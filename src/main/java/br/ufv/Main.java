package br.ufv;

import java.io.IOException;
import java.util.List;
import br.ufv.approaches.fpga.FPGA;
import br.ufv.dotTreeParser.Parser;
import br.ufv.dotTreeParser.treeStructure.Tree;

public class Main {

    public static void main(String[] args) throws IOException {
        Context.dataset = args[0];
        Context.datasetPath = args[1];
        Context.treesPath = args[2];
        Context.outputPath = args[3];
        Context.path = System.getProperty("user.dir");

        start();
    }

    public static void start() throws IOException{
        //PythonScriptCaller caller = new PythonScriptCaller();
        //caller.execute(path, dataset);

        List<Tree> trees = Parser.execute(Context.dataset);

        FPGA FPGAGenerator = new FPGA(
            trees, Context.dataset,
            Parser.getClassQuantity(),
            Parser.getFeatureQuantity(),
            false
        );

        FPGAGenerator.execute("conditional");

        //PythonDatasetParserCaller a = new PythonDatasetParserCaller();
        //a.execute(path, dataset);

        System.out.println("job finished: Success");
    }
}