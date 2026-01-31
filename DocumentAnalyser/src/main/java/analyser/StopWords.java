package DocumentAnalyser.src.main.java.analyser;

import java.util.Set;

public final class StopWords {

    private StopWords() {
        // prevent instantiation
    }

    public static final Set<String> WORDS = Set.of(
            "the", "and", "is", "at", "which", "on",
            "a", "an", "as", "are", "was", "were",
            "been", "be", "have", "has", "had",
            "do", "does", "did"
    );
}
