package com.example.myapplication;

public class SentimentResponse {
    private Candidate[] candidates;

    public String getSentiment() {
        if (candidates != null && candidates.length > 0 &&
                candidates[0].content != null && candidates[0].content.parts != null &&
                candidates[0].content.parts.length > 0) {
            return candidates[0].content.parts[0].text.trim(); // Loại bỏ khoảng trắng
        }
        return "Error";
    }

    class Candidate {
        private Content content;
    }

    class Content {
        private Part[] parts;
    }

    class Part {
        private String text;
    }
}