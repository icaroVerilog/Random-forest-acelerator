package project.src.java.core.randomForest.approaches.fpga.multiplexerGenerator;

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
import java.util.List;

public class TreeGenerator extends BaseTreeGenerator {

    private Integer precision;

    public void execute(List<Tree> trees, int classQuantity, int featureQuantity, SettingsCli settings){
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

        ReportGenerator reportGenerator = new ReportGenerator();
        ArrayList<Integer> nodeQntByTree = new ArrayList<>();

        for (int index = 0; index < trees.size(); index++){
            System.out.println("generating verilog decision tree" + index);

            nodeQntByTree.add(trees.get(index).getInnerNodes().size() + trees.get(index).getOuterNodes().size());

            String src = "";

            src += generateHeader(String.format("tree%d", index), featureQuantity);
            src += generatePortDeclaration(featureQuantity, classQuantity, this.precision);
            src += generateParameters(classQuantity);
            src += "\n\tassign voted_class = \n";
            src += generateMux(trees.get(index).getRoot(), 2);
            src += ";\n";
            src += generateEndDelimiters();

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
    }

    private String generateParameters(int classQuantity){
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
        return src;
    }

    private String generateMux(Node node, int tab){

        if (node instanceof OuterNode) {
            OuterNode newNode = (OuterNode) node;
            return tab(tab) + "class" + newNode.getClassNumber() + "\n";
        } else {
            InnerNode newNode = (InnerNode) node;
            String code = "";
            code += tab(tab) +"((" + generateComparison(newNode.getComparisson(), this.precision) +") ? \n";
            code += generateMux(newNode.getLeftNode(), tab + 1);
            code += tab(tab) + ":\n";
            code += generateMux(newNode.getRightNode(), tab + 1);
            code += tab(tab) + ")\n";

            return code;
        }
    }
}
