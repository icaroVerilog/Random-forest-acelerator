package project.src.java.relatory;

import project.src.java.util.FileBuilder;

import java.util.ArrayList;

public class ReportGenerator {

	private static String accuracy;

	public void generateReport(String dataset, String approach, Integer maxDepth, ArrayList<Integer> nodeQntByTree){
		String entryFileLine = "";

		int totalNodesQnt = 0;

		entryFileLine += String.format("dataset: %s\n", dataset);
		entryFileLine += String.format("approach: %s\n", approach);
		entryFileLine += accuracy + "\n";
		entryFileLine += String.format("tree max depth: %s\n", maxDepth);
		entryFileLine += String.format("total node quantity: %d\n", totalNodesQnt);

		for (int index = 0; index < nodeQntByTree.size(); index++) {
			totalNodesQnt += nodeQntByTree.get(index);
		}

		entryFileLine += String.format("total node quantity: %d\n", totalNodesQnt);

		for (int index2 = 0; index2 < nodeQntByTree.size(); index2++) {
			entryFileLine += String.format("tree%d node quantity: %d\n", index2, nodeQntByTree.get(index2));
		}
		entryFileLine += "=================================================\n";

		FileBuilder.execute(entryFileLine, "report.txt", true);
	}

	public void setAccuracy(String accuracyData){
		accuracy = accuracyData;
	}
}
