package DocumentAnalyser.src.test.java.analyser;

import DocumentAnalyser.src.main.java.analyser.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    @Test
    void shouldIgnoreStopWordsAndShortWords() {
        String text = "the and is java java ai";

        WordCounter counter = new WordCounter();
        Map<String, Integer> result = counter.countWords(text);

        assertEquals(1, result.size());
        assertEquals(2, result.get("java"));
    }

    @Test
    void shouldCountFrequenciesCorrectly() {
        String text = "java python java python python";

        WordCounter counter = new WordCounter();
        Map<String, Integer> result = counter.countWords(text);

        assertEquals(2, result.size());
        assertEquals(2, result.get("java"));
        assertEquals(3, result.get("python"));
    }

    @Test
    void shouldTrackTotalWordCount() {
        String text = "java python java";

        WordCounter counter = new WordCounter();
        counter.countWords(text);

        assertEquals(3, counter.getTotalWordCount());
    }

    @Test
    void shouldHandleEmptyInput() {
        WordCounter counter = new WordCounter();
        Map<String, Integer> result = counter.countWords("");

        assertTrue(result.isEmpty());
        assertEquals(0, counter.getTotalWordCount());
    }
}
