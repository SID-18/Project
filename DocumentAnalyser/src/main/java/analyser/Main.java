package DocumentAnalyser.src.main.java.analyser;

import DocumentAnalyser.src.main.java.analyser.StatisticsReport;
import DocumentAnalyser.src.main.java.analyser.TextReader;
import DocumentAnalyser.src.main.java.analyser.WordCounter;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java analyzer.Main <inputFilePath> <outputFilePath>");
            System.exit(1);
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        TextReader reader = new TextReader();
        String normalizedText = reader.readAndNormalize(inputFilePath);

        WordCounter counter = new WordCounter();
        Map<String, Integer> wordFrequency = counter.countWords(normalizedText);

        StatisticsReport report = new StatisticsReport(wordFrequency);
        report.exportReport(outputFilePath);

        System.out.println("Report generated at: " + outputFilePath);
    }
}
