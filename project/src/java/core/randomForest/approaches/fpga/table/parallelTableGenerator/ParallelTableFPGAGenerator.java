package project.src.java.core.randomForest.approaches.fpga.table.parallelTableGenerator;

import project.src.java.core.randomForest.approaches.fpga.AdderGenerator;
import project.src.java.core.randomForest.approaches.fpga.MajorityGenerator;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Tree;
import project.src.java.util.FileBuilder;
import project.src.java.util.executionSettings.CLI.ConditionalEquationMux.SettingsCli;
import java.util.List;

public class ParallelTableFPGAGenerator {
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

		var treeGenerator 	    = new TreeGenerator();
		var controllerGenerator = new ControllerGenerator();
		var adderGenerator      = new AdderGenerator();
		var majorityGenerator   = new MajorityGenerator();

		treeGenerator	   .execute(classQuantity, featureQuantity, treeList, settings);
		controllerGenerator.execute(classBitwidth, featureQuantity, treeList.size(), settings);
		adderGenerator     .execute(treeList.size(), settings);
		majorityGenerator  .execute(treeList.size(), classQuantity, settings);
	}
}
