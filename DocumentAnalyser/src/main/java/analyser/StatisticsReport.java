package DocumentAnalyser.src.main.java.analyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsReport {

    private final Map<String, Integer> wordFrequency;

    public StatisticsReport(Map<String, Integer> wordFrequency) {
        this.wordFrequency = wordFrequency;
    }

    public void exportReport(String outputFilePath) {
        StringBuilder report = new StringBuilder();

        report.append("Text Analysis Report\n");
        report.append("Total Words: ").append(getTotalWordCount()).append("\n");
        report.append("Unique Words: ").append(getUniqueWordCount()).append("\n");
        report.append("Average Word Length: ").append(getAverageWordLength()).append("\n");
        report.append("Longest Word: ").append(getLongestWord()).append("\n");
        report.append("Most Frequent Word: ").append(getMostFrequentWord()).append("\n\n");

        report.append("Top 10 Words:\n");
        for (Map.Entry<String, Integer> entry : getTopNWords(10)) {
            report.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("\n");
        }

        try {
            Files.writeString(Path.of(outputFilePath), report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export report", e);
        }
    }

    public int getTotalWordCount() {
        return wordFrequency.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getUniqueWordCount() {
        return wordFrequency.size();
    }

    public double getAverageWordLength() {
        int totalLength = wordFrequency.keySet()
                .stream()
                .mapToInt(String::length)
                .sum();

        return wordFrequency.isEmpty() ? 0.0 :
                (double) totalLength / wordFrequency.size();
    }

    public String getLongestWord() {
        return wordFrequency.keySet()
                .stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public String getMostFrequentWord() {
        return wordFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public List<Map.Entry<String, Integer>> getTopNWords(int n) {
        return wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<String> getWordsStartingWith(String prefix) {
        return wordFrequency.keySet()
                .stream()
                .filter(word -> word.startsWith(prefix))
                .sorted()
                .collect(Collectors.toList());
    }
}
