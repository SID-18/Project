package DocumentAnalyser.src.test.java.analyser;

import DocumentAnalyser.src.main.java.analyser.StatisticsReport;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsReportTest {

    private StatisticsReport createSampleReport() {
        Map<String, Integer> data = new HashMap<>();
        data.put("java", 3);
        data.put("python", 5);
        data.put("kotlin", 2);

        return new StatisticsReport(data);
    }

    @Test
    void shouldReturnTotalWordCount() {
        StatisticsReport report = createSampleReport();
        assertEquals(10, report.getTotalWordCount());
    }

    @Test
    void shouldReturnUniqueWordCount() {
        StatisticsReport report = createSampleReport();
        assertEquals(3, report.getUniqueWordCount());
    }

    @Test
    void shouldReturnAverageWordLength() {
        StatisticsReport report = createSampleReport();
        double avg = report.getAverageWordLength();

        assertTrue(avg > 4.0 && avg < 7.0);
    }

    @Test
    void shouldReturnLongestWord() {
        StatisticsReport report = createSampleReport();
        assertEquals("python", report.getLongestWord());
    }

    @Test
    void shouldReturnMostFrequentWord() {
        StatisticsReport report = createSampleReport();
        assertEquals("python", report.getMostFrequentWord());
    }

    @Test
    void shouldReturnTopNWords() {
        StatisticsReport report = createSampleReport();

        List<Map.Entry<String, Integer>> top2 = report.getTopNWords(2);

        assertEquals(2, top2.size());
        assertEquals("python", top2.get(0).getKey());
        assertEquals("java", top2.get(1).getKey());
    }

    @Test
    void shouldReturnWordsStartingWithPrefix() {
        StatisticsReport report = createSampleReport();

        List<String> words = report.getWordsStartingWith("py");

        assertEquals(1, words.size());
        assertEquals("python", words.get(0));
    }

    @Test
    void shouldHandleEmptyDataGracefully() {
        StatisticsReport report = new StatisticsReport(Map.of());

        assertEquals(0, report.getTotalWordCount());
        assertEquals(0, report.getUniqueWordCount());
        assertEquals("", report.getLongestWord());
        assertEquals("", report.getMostFrequentWord());
    }
}
