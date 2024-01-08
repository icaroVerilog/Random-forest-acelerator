package project.src.java.dotTreeParser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import project.src.java.dotTreeParser.treeStructure.Tree;
import project.src.java.dotTreeParser.treeStructure.TreeBuilder;

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

        var path = System.getProperty("user.dir") + "/project/assets/datasets/" + dataset + ".csv";
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
        var path = System.getProperty("user.dir") + "/project/assets/trees/" + dataset;
        var files = listFiles(path);
//        return files
//            .stream()
//            .sorted()
//            .map(file -> parseFromDot(path, file))
//            .collect(Collectors.toList());
        var a = files
                .stream()
                .sorted()
                .map(file -> parseFromDot(path, file))
                .collect(Collectors.toList());
        System.out.println(a);
        return a;
    }

    private static Tree parseFromDot(String path, String file){
        System.out.printf("arquivo: %s\n", file);
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
