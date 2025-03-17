package com.example.myapplication;

public class SentimentRequest {
    private Contents[] contents;
    private GenerationConfig generationConfig;

    public SentimentRequest(String text) {
        this.contents = new Contents[]{new Contents(new Parts[]{new Parts(
                "Analyze the sentiment of this sentence and return only one word: 'positive', 'negative', or 'neutral'. Sentence: " + text
        )})};
        this.generationConfig = new GenerationConfig();
    }

    class Contents {
        private Parts[] parts;

        Contents(Parts[] parts) {
            this.parts = parts;
        }
    }

    class Parts {
        private String text;

        Parts(String text) {
            this.text = text;
        }
    }

    class GenerationConfig {
        private int maxOutputTokens = 1; // Giới hạn đầu ra là 1 token (1 từ)
    }
}