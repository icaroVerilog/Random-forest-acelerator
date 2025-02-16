package project.src.java.core.randomForest.approaches.fpga;

import project.src.java.util.FileBuilder;
import project.src.java.util.executionSettings.CLI.ConditionalEquationMux.SettingsCli;

import java.util.ArrayList;
import java.util.Objects;

public class MajorityGenerator extends BasicGenerator {
    public void execute(int treeQnt, int classQnt, SettingsCli settings){
        String src = "";

        src += generateHeader("majority", classQnt);
        src += generatePortDeclaration(treeQnt, classQnt);
        src += generateMajorityExpression(classQnt);
        src += generateEndDelimiters();

        FileBuilder.execute(
            src, String.format(
                "output/%s_%s_%dtree_%sdeep_run/majority.v",
                settings.dataset,
                settings.approach,
                settings.trainingParameters.estimatorsQuantity,
                settings.trainingParameters.maxDepth
            ),
            false
        );
    }

    public String generateHeader(String module_name, int classQnt){
        String src = "";

        src += "module majority (\n";
        src += tab(1) + "voted,\n";

        for (int index = 0; index < classQnt; index++) {
            if (index == classQnt - 1){
                src += tab(1) + String.format("class%d_votes\n", index);
            } else {
                src += tab(1) + String.format("class%d_votes,\n", index);
            }

        }
        src += ");\n";
        return src;
    }

    private String generatePortDeclaration(int treeQnt, int classQnt){
        int bitwidth = (int) Math.ceil(Math.log(classQnt) / Math.log(2));

        String src = "";
        int sumBitwidth = (int)(Math.log(Math.abs(treeQnt)) / Math.log(2)) + 1;

        for (int index = 0; index < classQnt; index++){
            src += tab(1);
            src += generatePort(String.format("class%d_votes", index), WIRE, INPUT, sumBitwidth, true);
        }
        src += "\n";
        src += tab(1) + generatePort("voted", WIRE, OUTPUT, bitwidth, true);
        src += "\n";

        return src;
    }

    private String generateMajorityExpression(int classQnt){

        int classRepresentBitwidth = (int) Math.ceil(Math.log(classQnt) / Math.log(2));

        ArrayList<String> classBinary = new ArrayList<>();
        ArrayList<String> classWire = new ArrayList<>();

        for (int index = 0; index < classQnt; index++){
            classBinary.add(toBin(index, classRepresentBitwidth));
            classWire.add(String.format("class%d_votes", index));
        }

        int placeParenthesisCounter = 0;

        String src = tab(1) + "assign voted = ";
        boolean firstExpression = true;

        for (int index1 = 0; index1 < classQnt; index1++) {
            String comparison = String.format("(%d'b%s * (", classRepresentBitwidth, classBinary.get(index1));
            for (int index2 = 0; index2 < classQnt; index2++) {
                if (!Objects.equals(classBinary.get(index1), classBinary.get(index2))) {
                    if (index1 == classQnt - 1 && index2 == classQnt - 2) {
                        comparison += String.format("(%s > %s)", classWire.get(index1), classWire.get(index2));
                        if (placeParenthesisCounter == classQnt - 2){
                            comparison += "));";
                        }
                    } else {
                        if (placeParenthesisCounter == classQnt - 2){
                            comparison += String.format("(%s > %s))) + ", classWire.get(index1), classWire.get(index2));
                        } else {
                            comparison += String.format("(%s > %s) && ", classWire.get(index1), classWire.get(index2));
                        }
                    }
                    placeParenthesisCounter++;
                }
            }
            placeParenthesisCounter = 0;
            if (firstExpression) {
                src += comparison + "\n";
                firstExpression = false;
            } else {
                src += tab(3) + "       " + comparison + "\n";
            }
        }
        return src;
    }
}
