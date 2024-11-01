package project.src.java.core.randomForest.approaches.fpga.table.parallelTableGenerator;

import project.src.java.core.randomForest.approaches.fpga.BasicGenerator;
import project.src.java.util.FileBuilder;
import project.src.java.util.executionSettings.CLI.ConditionalEquationMux.SettingsCli;

public class ControllerGenerator extends BasicGenerator {

    private final String MODULE_NAME = "controller";

    private Integer precision;
    private int comparedColumnBitwidth;
    private int tableIndexerBitwidth;

    public void execute(int classBitwidth, int featureQuantity, int treeQuantity, SettingsCli settings){
        System.out.println("generating controller");

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

        this.comparedColumnBitwidth = 8;
        this.tableIndexerBitwidth   = 32;

        String src = "";

        src += generateHeader();
        src += generateIO(featureQuantity, classBitwidth);
        src += generateInternalVariables(treeQuantity);
        src += generateTreeInstantiation(treeQuantity);
        src += generateAlwaysBlock(classBitwidth, treeQuantity);

        FileBuilder.execute(
            src, String.format(
                "output/%s_%s_%dtree_%sdeep_run/controller.v",
                settings.dataset,
                settings.approach,
                settings.trainingParameters.estimatorsQuantity,
                settings.trainingParameters.maxDepth
            ),
            false
        );
    }


    private String generateHeader(){
        String src = "";

        src += "module controller (\n";
        src += tab(1) + "clock,\n";
        src += tab(1) + "reset,\n";
        src += tab(1) + "forest_vote,\n";
        src += tab(1) + "compute_vote,\n";
        src += tab(1) + "features\n";
        src += ");\n";

        return src;
    }

    private String generateIO(int featureQuantity, int classBitwidth){
        int featuresBusBitwidth = this.precision * featureQuantity;

        String src = "";

        src += tab(1) + generatePort("clock", WIRE, INPUT, 1, true);
        src += tab(1) + generatePort("reset", WIRE, INPUT, 1, true);
        src += "\n";
        src += tab(1) + generatePort("features", WIRE, INPUT, featuresBusBitwidth, true);
        src += "\n";
        src += tab(1) + generatePort("forest_vote", REGISTER, OUTPUT, classBitwidth, true);
        src += tab(1) + generatePort("compute_vote", REGISTER, OUTPUT, 1, true);
        src += "\n";

        return src;
    }

    private String generateInternalVariables(int treeQuantity){
        String src = "";

        for (int index = 0; index < treeQuantity; index++) {
            src += tab(1) + generatePort(String.format("tree%d_compute_vote_w", index), WIRE, NONE, 1, true);
        }
        src += "\n";

        for (int index = 0; index < treeQuantity; index++) {
            src += tab(1) + generatePort(String.format("tree%d_compute_vote", index), REGISTER, NONE, 1, true);
        }
        src += "\n";

        src += tab(1) + generatePort("forest_vote_available", REGISTER, NONE, 1, true);
        src += tab(1) + generatePort("start_next", REGISTER, NONE, 1, true);
        src += "\n";

        return src;
    }

    private String generateTreeInstantiation(int treeQuantity){
        String src = "";

        for (int index = 0; index < treeQuantity; index++) {
            src += tab(1) + String.format("tree%d tree%d(\n", index, index);
            src += tab(2) + ".clock(clock),\n";
            src += tab(2) + ".reset(reset),\n";
            src += tab(2) + ".start_next(start_next),\n";
            src += tab(2) + String.format(".voted_class(tree%d_vote_w),\n", index);
            src += tab(2) + String.format(".compute_vote(tree%d_compute_vote_w),\n", index);
            src += tab(2) + ".features(features)\n";
            src += tab(1) + ");\n\n";
        }
        return src;
    }

    private String generateAlwaysBlock(int classBitwidth, int treeQuantity){
        String computeVoteConditional = CONDITIONAL_BLOCK;
        String computeVoteExpr = "reset";
        String computeVoteBody = "";

        computeVoteBody += tab(3) + "compute_vote <= 1'b0;\n";
        computeVoteBody += tab(3) + String.format(
            "forest_vote <= %d'b%s;\n",
            classBitwidth,
            toBin(0, classBitwidth)
        );

        computeVoteConditional = computeVoteConditional
            .replace("x", computeVoteExpr)
            .replace("`", computeVoteBody)
            .replace("ind", tab(2));


        String triggerTreeComputeVoteConditionals = "";

        for (int index = 0; index < treeQuantity; index++) {
            String triggerTreeComputeVoteConditional = CONDITIONAL_BLOCK;
            String triggerTreeComputeVoteExpr = String.format(
                "tree%d_compute_vote_w",
                index
            );
            String triggerTreeComputeVoteBody = String.format(
                tab(4) + "tree%d_compute_vote <= 1'b1;\n",
                index
            );

            triggerTreeComputeVoteConditional = triggerTreeComputeVoteConditional
                .replace("x", triggerTreeComputeVoteExpr)
                .replace("`", triggerTreeComputeVoteBody)
                .replace("ind", tab(3));

            triggerTreeComputeVoteConditionals += triggerTreeComputeVoteConditional + "\n";
        }

        String computeVoteVerifyConditional = CONDITIONAL_BLOCK;
        String computeVoteVerifyConditionalExpr = "";
        String computeVoteVerifyConditionalBody = "";

        for (int index = 0; index < treeQuantity; index++) {
            if (index + 1 == treeQuantity){
                computeVoteVerifyConditionalExpr += String.format(
                    "tree%d_compute_vote",
                    index
                );
            } else {
                computeVoteVerifyConditionalExpr += String.format(
                    "tree%d_compute_vote && ",
                    index
                );
            }
        }

        for (int index = 0; index < treeQuantity; index++) {
            computeVoteVerifyConditionalBody += String.format(
                tab(4) + "tree%d_vote <= tree%d_vote_w;\n",
                index,
                index
            );
        }

        for (int index = 0; index < treeQuantity; index++) {
            computeVoteVerifyConditionalBody += String.format(
                tab(4) + "tree%d_compute_vote <= 1'b0;\n",
                index
            );
        }
        computeVoteVerifyConditionalBody += tab(4) + "forest_vote_available <= 1'b1;\n";

        computeVoteVerifyConditional = computeVoteVerifyConditional
            .replace("x", computeVoteVerifyConditionalExpr)
            .replace("`", computeVoteVerifyConditionalBody)
            .replace("ind", tab(3));


        String forestVoteAvailableConditional = CONDITIONAL_BLOCK;
        String forestVoteAvailableConditionalExpr = "forest_vote_available";
        String forestVoteAvailableConditionalBody = "";

        forestVoteAvailableConditionalBody += tab(4) + "forest_vote <= forest_vote_w;\n";
        forestVoteAvailableConditionalBody += tab(4) + "compute_vote <= 1'b1;\n";
        forestVoteAvailableConditionalBody += tab(4) + "forest_vote_available <= 1'b0;\n";
        forestVoteAvailableConditionalBody += tab(4) + "start_next <= 1'b1;\n";

        forestVoteAvailableConditional = forestVoteAvailableConditional
            .replace("x", forestVoteAvailableConditionalExpr)
            .replace("`", forestVoteAvailableConditionalBody)
            .replace("ind", tab(3));


        String forestVoteAvailableConditionalElse = CONDITIONAL_ELSE_BLOCK;
        String forestVoteAvailableConditionalElseBody = "";

        forestVoteAvailableConditionalElseBody += tab(4) + "compute_vote <= 1'b0;\n";
        forestVoteAvailableConditionalElseBody += tab(4) + "start_next <= 1'b0;\n";

        forestVoteAvailableConditionalElse = forestVoteAvailableConditionalElse
            .replace("y", forestVoteAvailableConditionalElseBody)
            .replace("ind", tab(3));


        String computeVoteConditionalElse = CONDITIONAL_ELSE_BLOCK;
        String computeVoteConditionalElseBody = "";

        computeVoteConditionalElseBody += triggerTreeComputeVoteConditionals + "\n";
        computeVoteConditionalElseBody += computeVoteVerifyConditional + "\n\n";
        computeVoteConditionalElseBody += forestVoteAvailableConditional + "\n";
        computeVoteConditionalElseBody += forestVoteAvailableConditionalElse;

        computeVoteConditionalElse = computeVoteConditionalElse
            .replace("y", computeVoteConditionalElseBody)
            .replace("ind", tab(2));

        String always = ALWAYS_BLOCK;
        String src = computeVoteConditional + "\n" + computeVoteConditionalElse;

        always = always
            .replace("border", "posedge")
            .replace("signal", "clock")
            .replace("src", src)
            .replace("ind", tab(1));

        return always + "endmodule";
    }
}
