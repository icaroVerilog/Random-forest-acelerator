package br.ufv.approaches.fpga;

import br.ufv.Context;
import br.ufv.util.FileBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DatasetParser {

    private static final int BITWIDTH = 12;
    public int readDataset(String dataset) throws IOException {

        ArrayList<String> featuresVal = new ArrayList<>();

        var path = Context.datasetPath;
        var scanner = new Scanner(new File(path));
        var line = scanner.nextLine().split(",");

        int datasetDepth = 0;

        String data = "";

        while (scanner.hasNext()){
            line = scanner.nextLine().split(",");
            ArrayList<String> lineArray = new ArrayList<>(List.of(line));

            lineArray.remove(lineArray.size() - 1);
            String aux = "";

            for (int index = 0; index < lineArray.size(); index++){
                String exponent = valueToBinary(Integer.valueOf(lineArray.get(index)), BITWIDTH);
                aux += exponent;
            }

            aux+= "\n";
            data += aux;

            datasetDepth++;
        }

        scanner.close();

        FileBuilder.createDir("FPGA");
        FileBuilder.createDir("FPGA/" + dataset);
        FileBuilder.createDir("FPGA/" + dataset + "/dataset");
        FileBuilder.execute(data, "FPGA/" + dataset + "/dataset/" + "data.bin");

        return datasetDepth;
    }

    private String valueToBinary(Integer value, Integer bitwidth){
        return String.format("%" + bitwidth + "s", Integer.toBinaryString(value)).replaceAll(" ", "0");
    }
}
