package project.src.java.core.randomForest.approaches.fpga.pipeline;

import project.src.java.core.randomForest.approaches.fpga.BaseTreeGenerator;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Nodes.InnerNode;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Nodes.Node;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Nodes.OuterNode;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Tree;
import project.src.java.util.FileBuilder;
import project.src.java.util.executionSettings.CLI.ConditionalEquationMux.SettingsCli;
import project.src.java.relatory.ReportGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TreeGenerator extends BaseTreeGenerator {

	private int precision;
	private int maxDepth;

	public void execute(List<Tree> treeList, int classQuantity, int featureQuantity, SettingsCli settings){

		switch (settings.inferenceParameters.precision){
			case "double":
				this.precision = DOUBLE_PRECISION;
				break;
			case "normal":
				this.precision = NORMAL_PRECISION;
				break;
			case "half":
				this.precision = HALF_PRECISION;
				break;
			default:
				this.precision = 0;
				break;
		}

		this.maxDepth = 0;

		ReportGenerator reportGenerator = new ReportGenerator();
		ArrayList<Integer> nodeQntByTree = new ArrayList<>();

		for (int index = 0; index < treeList.size(); index++) {
			if (treeList.get(index).getMaxDepth() > this.maxDepth) {
				this.maxDepth = treeList.get(index).getMaxDepth();
			}
		}

		for (int index = 0; index < treeList.size(); index++){
			System.out.println("generating verilog decision tree" + index);
			Tree currentTree = treeList.get(index);
			nodeQntByTree.add(currentTree.getInnerNodes().size() + currentTree.getOuterNodes().size());

			String src = "";

			src += generateHeader(index);
			src += generateIEE754ComparatorFunction(this.precision);
			src += generateParameters(currentTree.innerNodes, classQuantity);
			src += generatePortDeclaration(featureQuantity, classQuantity, currentTree.getInnerNodes().size(), currentTree.getMaxDepth());
			src += generateAlwaysBlock(featureQuantity, currentTree.innerNodes, currentTree.getMaxDepth());

			FileBuilder.execute(
				src, String.format(
					"output/%s_%s_%dtree_%sdeep_run/tree%d.v",
					settings.dataset,
					settings.approach,
					settings.trainingParameters.estimatorsQuantity,
					settings.trainingParameters.maxDepth,
					index
				),
				false
			);
		}

		reportGenerator.createEntry(
			settings.dataset,
			settings.approach,
			settings.trainingParameters.maxDepth,
			nodeQntByTree
		);

		reportGenerator.generateReport();
	}

	public String generateHeader(int treeIndex){
		String src = "";

		src += String.format("module tree%d (\n", treeIndex);

		src += tab(1) + "clock,\n";
		src += tab(1) + "reset,\n";
		src += tab(1) + "voted_class,\n";
		src += tab(1) + "compute_vote,\n";
		src += tab(1) + "features\n";
		src += ");\n";

		return src;
	}

	public String generateParameters(HashMap<Integer, InnerNode> innerNodes, int classQuantity){
		int[][] oneHotMatrix = new int[classQuantity][classQuantity];

		for (int i = 0; i < oneHotMatrix.length; i++) {
			for (int j = 0; j < oneHotMatrix[i].length; j++) {
				if (i == j){
					oneHotMatrix[i][j] = 1;
				}
				else {
					oneHotMatrix[i][j] = 0;
				}
			}
		}

		String src = "";

		for (int index = 0; index < classQuantity; index++) {
			String oneHotEncode = Arrays.toString(oneHotMatrix[classQuantity - index - 1])
					.replaceAll("[\\[\\]\\s]", "")
					.replace(",", "") + ";";
			src += tab(1) + String.format("parameter class%d = %d'b%s\n", index, classQuantity,  oneHotEncode);
		}
		src += "\n";

		int counter = 0;

		for (int key: innerNodes.keySet()){

			double threshold = innerNodes.get(key).getComparisson().getThreshold();

			src += tab(1) + String.format(
				"parameter threshold%d_%d = %d'b%s;\n",
				counter,
				innerNodes.get(key).getComparisson().getColumn(),
				this.precision,
				toIEEE754(threshold, this.precision)
			);
			counter++;
		}
		src += "\n";
		return src;
	}

	public String generatePortDeclaration(int featureQuantity, int classQuantity, int innerNodeQnt, int maxDepth){
		String tab = tab(1);
		String src = "";

		src += tab + "input wire clock;\n";
		src += tab + "input wire reset;\n\n";
		src += tab(1) + generatePort("features", WIRE, INPUT, this.precision * featureQuantity, true);
		src += "\n";
		src += tab(1) + generatePort("voted_class", REGISTER, OUTPUT, classQuantity, true);
		src += tab(1) + generatePort("compute_vote", REGISTER, OUTPUT, 1, true);
		src += "\n";

		for (int index = 0; index < featureQuantity; index++){
			src += tab(1) + generatePort(String.format("feature%d", index), REGISTER, NONE, this.precision, true);
		}

		src += "\n";
		src += tab(1) + generatePort("comparison", REGISTER, NONE, innerNodeQnt, true);
		src += "\n";

		for (int index = 0; index < innerNodeQnt; index++) {
			src += tab(1) + generatePort(String.format("node%d", index), REGISTER, NONE, classQuantity, true);
		}

		if (this.maxDepth > maxDepth){
			for (int index = 0; index < this.maxDepth - maxDepth; index++) {
				src += tab(1) + generatePort(String.format("delay_node%d", index), REGISTER, NONE, classQuantity, true);
			}
		}

		src += "\n";
		if (this.maxDepth > maxDepth){
			int additionalBits = this.maxDepth - maxDepth;
			src += tab(1) + generatePort("sync_flag", REGISTER, NONE, maxDepth + 2 + additionalBits, true);
		} else {
			src += tab(1) + generatePort("sync_flag", REGISTER, NONE, maxDepth + 2, true);
		}

		src += "\n";

		return src;
	}

	public String generateAlwaysBlock(int featureQuantity, HashMap<Integer, InnerNode> innerNodes, int maxDepth){
		String src = "";

		for (int index = 0; index < featureQuantity; index++) {
			int upperBit = ((featureQuantity * this.precision) - 1) - (index * this.precision);
			int lowerBit = ((featureQuantity * this.precision)) - ((index + 1) * this.precision);
			src += tab(2) + String.format("feature%d <= features[%d:%d];\n", index, upperBit, lowerBit);
		}
		src += tab(2) + "sync_flag[0] <= ~reset;\n";
		src += "\n";

		int maxLevel = 0;
		int counter = 0;

		ArrayList<Integer> innerNodeList = new ArrayList<>();

		for (int key: innerNodes.keySet()){
			innerNodeList.add(innerNodes.get(key).getId());

			src += tab(2) + String.format(
				"comparison[%d] <= IEEE754_comparator(threshold%d_%d, feature%d);\n",
				counter,
				counter,
				innerNodes.get(key).getComparisson().getColumn(),
				innerNodes.get(key).getComparisson().getColumn()
			);

			counter = counter + 1;
		}

		int comparisonCounter = innerNodes.keySet().size() - 1;
		int maxDepthCounter = 1;

		ArrayList<ArrayList<Integer>> delayMatrix = new ArrayList<>();
		ArrayList<String> delayRegisters = new ArrayList<>();

		for (int index = maxDepth - 1; index >= 0 ; index--) {
			ArrayList<Integer> delayedComparisons = new ArrayList<>();

			Boolean placeDelay = false;

			String levelSyncConditional = CONDITIONAL_BLOCK;
			String levelSyncBody = "";

			for (int key: innerNodes.keySet()){
				if (innerNodes.get(key).getLevel() == index){
					String nodeConditionalTrue = CONDITIONAL_BLOCK;
					String nodeConditionalFalse = CONDITIONAL_ELSE_BLOCK;
					String nodeExpr = "";

					if (maxDepthCounter > 1 ){
						nodeExpr = String.format("c%d_bypass_r%d",  comparisonCounter, maxDepthCounter - 1);
						delayedComparisons.add(comparisonCounter);
						placeDelay = true;
					} else {
						nodeExpr = String.format("comparison[%d]", comparisonCounter);
					}

					String nodeBodyTrue = "";
					String nodeBodyFalse = "";

					Node leftNode = innerNodes.get(key).getLeftNode();
					Node rightNode = innerNodes.get(key).getRightNode();

					if (leftNode instanceof InnerNode){
						nodeBodyTrue = tab(4) + String.format(
							"node%d <= node%d;\n",
							comparisonCounter,
							innerNodeList.indexOf(leftNode.getId())
						);
					}
					else if (leftNode instanceof OuterNode) {
						nodeBodyTrue = tab(4) + String.format(
							"node%d <= class%d;\n",
							comparisonCounter,
							((OuterNode) leftNode).getClassNumber()
						);
					}

					if (rightNode instanceof InnerNode){
						nodeBodyFalse = tab(4) + String.format(
							"node%d <= node%d;\n",
							comparisonCounter,
							innerNodeList.indexOf(rightNode.getId())
						);
					}
					else if (rightNode instanceof OuterNode) {
						nodeBodyFalse = tab(4) + String.format(
							"node%d <= class%d;\n",
							comparisonCounter,
							((OuterNode) rightNode).getClassNumber()
						);
					}

					nodeConditionalTrue = nodeConditionalTrue
						.replace("x", nodeExpr)
						.replace("`", nodeBodyTrue)
						.replace("ind", tab(3));

					nodeConditionalFalse = nodeConditionalFalse
						.replace("y", nodeBodyFalse)
						.replace("ind", tab(3));

					levelSyncBody += nodeConditionalTrue + "\n" + nodeConditionalFalse;
					comparisonCounter--;
				}
			}

			delayMatrix.add(delayedComparisons);
			if (placeDelay){
				src += "^\n";
			}

			src += tab(2) + String.format("sync_flag[%d] <= sync_flag[%d];\n", maxDepthCounter, maxDepthCounter - 1);
			src += "\n";

			String levelSyncExpr = String.format("sync_flag[%d] == 1'b1", maxDepthCounter);

			levelSyncConditional = levelSyncConditional
				.replace("x", levelSyncExpr)
				.replace("`", levelSyncBody)
				.replace("ind", tab(2));

			maxDepthCounter++;
			src += levelSyncConditional + "\n";
			src += "\n";
		}

		for (int index = delayMatrix.size(); index > 0; index--) {
			if (index != delayMatrix.size()){
				delayMatrix.get(index - 1).addAll(delayMatrix.get(index));
			}
		}

		for (int index1 = 1; index1 < delayMatrix.size(); index1++) {
			String delay = "";

			for (int index2 = 0; index2 < delayMatrix.get(index1).size(); index2++) {
				if (index1 == 1){
					delay += tab(2) + String.format(
						"c%d_bypass_r%d <= comparison[%d];\n",
						delayMatrix.get(index1).get(index2),
						index1,
						delayMatrix.get(index1).get(index2)
					);
				} else {
					delay += tab(2) + String.format(
						"c%d_bypass_r%d <= c%d_bypass_r%d;\n",
						delayMatrix.get(index1).get(index2),
						index1,
						delayMatrix.get(index1).get(index2),
						index1 - 1
					);
				}

				delayRegisters.add(
					String.format("c%d_bypass_r%d", delayMatrix.get(index1).get(index2), index1)
				);
			}
			src = src.replaceFirst("\\^", delay);
		}

		if (this.maxDepth > maxDepth){
			for (int index = 0; index < this.maxDepth - maxDepth; index++) {
				src += tab(2) + String.format("sync_flag[%d] <= sync_flag[%d];\n", maxDepthCounter, maxDepthCounter - 1);
				maxDepthCounter++;
			}
		}

		src += tab(2) + String.format("sync_flag[%d] <= sync_flag[%d];\n", maxDepthCounter, maxDepthCounter - 1);
		src += tab(2) + String.format("compute_vote <= sync_flag[%d];\n", maxDepthCounter);

		if (this.maxDepth > maxDepth){
			for (int index = 0; index < this.maxDepth - maxDepth; index++) {
				if (index == 0){
					src += tab(2) + String.format("delay_node%d <= node0;\n", index);
				} else {
					src += tab(2) + String.format("delay_node%d <= delay_node%d;\n", index, index - 1);
				}
			}
			src += tab(2) + String.format("voted_class <= delay_node%d;\n", (this.maxDepth - maxDepth) - 1);
		} else {
			src += tab(2) + "voted_class <= node0;\n";
		}

		String registers = "";

		for (int index = 0; index < delayRegisters.size(); index++) {
			registers += tab(1) + generatePort(delayRegisters.get(index), REGISTER, NONE, 1, true);
		}

		String always = ALWAYS_BLOCK;
		always = always
			.replace("border", "posedge")
			.replace("signal", "clock")
			.replace("src", src)
			.replace("ind", tab(1));

		return registers + always + "endmodule";
	}
}
