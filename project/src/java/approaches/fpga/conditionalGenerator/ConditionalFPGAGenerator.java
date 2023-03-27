package project.src.java.approaches.fpga.conditionalGenerator;

import project.src.java.dotTreeParser.treeStructure.Tree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConditionalFPGAGenerator {
    public void execute(List<Tree> treeList, Integer classQnt, Integer featureQnt, Integer samplesQnt, Boolean debugMode){

        var controllerGenerator = new ControllerGenerator();
        var treeGenerator = new TreeGenerator();

        treeGenerator.execute(treeList, classQnt, featureQnt);
        controllerGenerator.execute(treeList.size(), classQnt, featureQnt, samplesQnt, debugMode);


    }

}
