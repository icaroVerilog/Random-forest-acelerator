package project.src.java.core.randomForest.approaches.fpga.table.tableGenerator;

import project.src.java.core.randomForest.approaches.fpga.table.TableEntryGenerator;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Tree;
import project.src.java.util.FileBuilder;
import project.src.java.util.executionSettings.CLI.ConditionalEquationMux.SettingsCli;


import java.util.List;

public class TableFPGAGenerator {

    public void execute(List<Tree> treeList, int classQuantity, int featureQuantity, SettingsCli settings){

        FileBuilder.createDir(
            String.format(
                "output/%s_%s_%dtree_%sdeep_run",
                settings.dataset,
                settings.approach,
                settings.trainingParameters.estimatorsQuantity,
                settings.trainingParameters.maxDepth
            )
        );

        /* calculate the needed bitwidth to represent each class */
        int classBitwidth = (int) Math.ceil(Math.log(classQuantity) / Math.log(2));

        var validationTableGenerator = new ValidationTableGenerator();
        var controllerGenerator      = new ControllerGenerator();

        validationTableGenerator.execute(classQuantity, featureQuantity, classBitwidth, treeList, settings);
        controllerGenerator     .execute(classBitwidth, featureQuantity, settings);
    }
}
