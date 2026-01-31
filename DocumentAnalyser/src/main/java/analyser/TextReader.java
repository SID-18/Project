package DocumentAnalyser.src.main.java.analyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReader {

    public String readAndNormalize(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            return normalize(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private String normalize(String text) {
        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9 ]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
