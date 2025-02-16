package project.src.java.core.randomForest.parsers.dotTreeParser;

import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.Tree;
import project.src.java.core.randomForest.parsers.dotTreeParser.treeStructure.TreeBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    public static List<String> featuresNames;
    public static Set<String> classesNames;

    public static List<Tree> execute(String dataset) throws IOException {
        readDatasetHeader(dataset);
        return readDatasetSamples(dataset);
    }

    public static int getClassQuantity(){
        return classesNames.size();
    }

    public static int getFeatureQuantity(){

        /* the number of feature also count the class column, subtracting 1 to return the correct value */

        return featuresNames.size() - 1;
    }

    private static void readDatasetHeader(String dataset) throws IOException {

        /*TODO: ALTERAR FUTURAMENTE PARA PESQUISAR NA PASTA QUE O USUÁRIO TIVER O TERMINAL ABERTO, COMO EM OUTRAS CLI'S*/
        var path = System.getProperty("user.dir") + "/datasets/" + dataset;
        var scanner = new Scanner(new File(path));
        var line = scanner.nextLine().split(",");

        featuresNames = Arrays.asList(line);
        classesNames = new HashSet<String>();

        while(scanner.hasNext()){
            line = scanner.nextLine().split(",");
            classesNames.add(line[line.length - 1]);
        }
        scanner.close();
    }

    private static List<Tree> readDatasetSamples(String dataset) throws IOException {
        var path = System.getProperty("user.dir") + "/trees/" + dataset;
        var files = listFiles(path);
        var a = files
                .stream()
                .sorted()
                .map(file -> parseFromDot(path, file))
                .collect(Collectors.toList());
        return a;
    }

    private static Tree parseFromDot(String path, String file){
        try {
            return TreeBuilder.execute(path+"/"+file, featuresNames, classesNames);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static Set<String> listFiles(String path) {
        return Stream.of(new File(path).listFiles())
          .filter(file -> !file.isDirectory())
          .map(File::getName)
          .collect(Collectors.toSet());
    }

}
