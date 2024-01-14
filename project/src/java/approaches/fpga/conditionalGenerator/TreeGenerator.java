package project.src.java.approaches.fpga.conditionalGenerator;

import project.src.java.approaches.fpga.BasicGenerator;
import project.src.java.dotTreeParser.treeStructure.Comparisson;
import project.src.java.dotTreeParser.treeStructure.Nodes.InnerNode;
import project.src.java.dotTreeParser.treeStructure.Nodes.Node;
import project.src.java.dotTreeParser.treeStructure.Nodes.OuterNode;
import project.src.java.dotTreeParser.treeStructure.Tree;
import project.src.java.util.FileBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeGenerator extends BasicGenerator {


    public void execute(List<Tree> trees, Integer classQnt, Integer featureQnt, String dataset){

        for (int index = 0; index < trees.size(); index++){

            System.out.println("generating verilog decision tree" + index);

            String sourceCode = "";

            sourceCode += generateHeader(index, featureQnt);
            sourceCode += generatePortDeclaration(featureQnt, classQnt);
            sourceCode += generateAlwaysBlock();
            sourceCode += generateConditionals(trees.get(index).getRoot(), 2);
            sourceCode += generateEndDelimiters();

            FileBuilder.createDir("FPGA/" + dataset);
            FileBuilder.execute(sourceCode, "FPGA/" + dataset + "/tree" + index + ".v");
        }
    }

    public String generateHeader(int treeIndex, int featureQnt){

        String tab = generateTab(1);
        String header = "module tree" + treeIndex + "(\n";
        String FI = IntStream.range(0, featureQnt)
                .mapToObj(index -> "ft" + index)
                .collect(Collectors.joining(", ")
        );
        String clkAndOut = "clock, voted_class";

        return header + tab + FI + ",\n" +
               tab + clkAndOut + "\n);\n";
    }

    public String generatePortDeclaration(int featureQnt, int classQnt){
        String tab = generateTab(1);

        String CLK = tab + "input wire clock;\n\n";

        String FI = IntStream.range(0, featureQnt)
                .mapToObj(index -> tab + "input wire [31:0] ft" + index + ";\n")
                .collect(Collectors.joining("")
        );

        int bitWidth = (int) Math.ceil(Math.sqrt(classQnt));
        String votedClass =  tab + "output reg [" + (bitWidth) + ":0] voted_class;\n\n";

        int[][] oneHotMatrix = new int[classQnt][classQnt];

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

        String CL = IntStream.range(0, classQnt)
                .mapToObj(
                        index -> tab + "parameter class" + index + " = " + (bitWidth + 1) + "'b" +
                                Arrays.toString(oneHotMatrix[index])
                                        .replaceAll("[\\[\\]\\s]", "")
                                        .replace(",", "") + ";"
                )
                .collect(Collectors.joining("\n")
        );
        return CLK + FI + "\n"  + votedClass + CL;
    }

    public String generateAlwaysBlock(){

        String tab = generateTab(1);

        return "\n\n" + tab + "always @(posedge clock) begin\n";
    }

    public String generateConditionals(Node node, int tab){

        var tabs = IntStream.range(0, tab)
                .mapToObj(t -> "\t")
                .collect(Collectors.joining("")
        );

        if (node instanceof OuterNode){
            OuterNode newNode = (OuterNode) node;
            return tabs + "voted_class <= class" + newNode.getClassNumber() + ";\n";
        }
        else {
            InnerNode newNode = (InnerNode) node;
            String code = "";
            code += tabs + "if (" + generateComparison(newNode.getComparisson()) +") begin\n";
            code += generateConditionals(newNode.getLeftNode(), tab + 1);
            code += tabs + "end \n" + tabs + "else begin\n";
            code += generateConditionals(newNode.getRightNode(), tab + 1);
            code += tabs + "end\n";

            return code;
        }
    }

    public String generateComparison(Comparisson c){

        var threshold = c.getThreshold().toString().split("\\.");
        String integralThreshold = Integer.toBinaryString(Integer.parseInt(threshold[0]));

        String binaryIntegralTh = String.format(FEATURE_BITWIDTH + "'b%" + FEATURE_BITWIDTH + "s", integralThreshold).replaceAll(" ", "0");
        String first = "";

        System.out.println(c.getComparissonType());

        first += "ft" + c.getColumn() + " " + c.getComparissonType() + " " + binaryIntegralTh;
        return first;
    }

    public String generateEndDelimiters(){
        String code = "";

        code += generateTab(1) + "end\n";
        code += "endmodule";

        return code;
    }

    public String generateTab(int tab){
        return IntStream.range(0, tab)
                .mapToObj(t -> "\t")
                .collect(Collectors.joining("")
        );
    }
}
