package DocumentAnalyser.src.test.java.analyser;

import DocumentAnalyser.src.main.java.analyser.TextReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldReadAndNormalizeText() throws Exception {
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, "Hello, World!! This IS a TEST.");

        TextReader reader = new TextReader();
        String result = reader.readAndNormalize(file.toString());

        assertEquals("hello world this is a test", result);
    }

    @Test
    void shouldHandleMultipleSpaces() throws Exception {
        Path file = tempDir.resolve("spaces.txt");
        Files.writeString(file, "Hello     World");

        TextReader reader = new TextReader();
        String result = reader.readAndNormalize(file.toString());

        assertEquals("hello world", result);
    }
}

