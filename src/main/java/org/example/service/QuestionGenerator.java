package org.example.service;

import java.util.Random;

public class QuestionGenerator {
    private static final Random random = new Random();

    public static void main(String[] args) {
        generateQuestions(1000);
    }

    public static void generateQuestions(int count) {
        for (int i = 1; i <= count; i++) {
            String question = generateQuestion(i);
            System.out.println(i + ". " + question);
        }
    }

    public static String generateQuestion(int id) {
        // Use the ID to ensure uniqueness
        int category = id % 4; // 4 categories

        return switch (category) {
            case 0 -> generateMathQuestion(id);
            case 1 -> generateScienceQuestion(id);
            case 2 -> generateHistoryQuestion(id);
            case 3 -> generateGeneralKnowledgeQuestion(id);
            default -> "What is the meaning of life?";
        };
    }

    private static String generateMathQuestion(int seed) {
        int a = (seed * 3) % 50 + 1;
        int b = (seed * 7) % 50 + 1;
        int operation = seed % 4;

        return switch (operation) {
            case 0 -> "What is " + a + " + " + b + "?";
            case 1 -> "What is " + a + " - " + b + "?";
            case 2 -> "What is " + a + " × " + b + "?";
            case 3 -> "What is " + a + " ÷ " + b + " (rounded to nearest integer)?";
            default -> "Calculate the square root of " + (a * a);
        };
    }

    private static String generateScienceQuestion(int seed) {
        String[] elements = {"Hydrogen", "Oxygen", "Carbon", "Nitrogen", "Iron", "Gold", "Silver"};
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        String[] concepts = {"gravity", "photosynthesis", "evolution", "relativity", "quantum mechanics"};

        int type = seed % 3;
        return switch (type) {
            case 0 -> "What is the atomic number of " + elements[seed % elements.length] + "?";
            case 1 -> "Which planet is known as the " +
                    (seed % 2 == 0 ? "red planet" : "gas giant") + "?";
            case 2 -> "Who proposed the theory of " + concepts[seed % concepts.length] + "?";
            default -> "What is the chemical formula for water?";
        };
    }

    private static String generateHistoryQuestion(int seed) {
        String[] events = {"World War I", "World War II", "the Renaissance", "the Industrial Revolution",
                "the French Revolution", "the American Civil War"};
        String[] figures = {"Napoleon", "Julius Caesar", "George Washington", "Abraham Lincoln",
                "Winston Churchill", "Mahatma Gandhi"};
        String[] dates = {"1066", "1492", "1776", "1914", "1945", "1969"};

        int type = seed % 3;
        return switch (type) {
            case 0 -> "In what year did " + events[seed % events.length] + " begin?";
            case 1 -> "Who was the leader during " + events[seed % events.length] + "?";
            case 2 -> "What significant historical event happened in " + dates[seed % dates.length] + "?";
            default -> "When was the Declaration of Independence signed?";
        };
    }

    private static String generateGeneralKnowledgeQuestion(int seed) {
        String[] countries = {"France", "Brazil", "Japan", "Australia", "Canada", "India", "Egypt"};
        String[] capitals = {"Paris", "Tokyo", "Canberra", "Ottawa", "New Delhi", "Cairo"};
        String[] languages = {"English", "Spanish", "Mandarin", "Hindi", "Arabic", "French"};

        int type = seed % 3;
        return switch (type) {
            case 0 -> "What is the capital of " + countries[seed % countries.length] + "?";
            case 1 -> "How many continents are there on Earth?";
            case 2 -> "Which language has the most native speakers?";
            default -> "What is the largest ocean on Earth?";
        };
    }
}
