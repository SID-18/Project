package DocumentAnalyser.src.main.java.analyser;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public Map<String, Integer> countWords(String normalizedText) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        if (normalizedText == null || normalizedText.isEmpty()) {
            return frequencyMap;
        }

        String[] tokens = normalizedText.split(" ");

        for (String word : tokens) {
            if (word.length() < 3) {
                continue;
            }
            if (StopWords.WORDS.contains(word)) {
                continue;
            }

            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        return frequencyMap;
    }

}
