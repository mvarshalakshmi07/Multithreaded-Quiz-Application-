package QuizApp;

import java.util.*;

public class QuizService {
    private List<Question> questions;
    private int score = 0;
    private int currentIndex = 0;

    public QuizService() {
        questions = new ArrayList<>();

        questions.add(new Question("Java is?", 
            new String[]{"Language", "OS", "Browser", "Hardware"}, 0));

        questions.add(new Question("Thread is?", 
            new String[]{"Process", "Lightweight Process", "Program", "Compiler"}, 1));
    }

    public Question getCurrentQuestion() {
        return questions.get(currentIndex);
    }

    public void nextQuestion() {
        currentIndex++;
    }

    public boolean hasNext() {
        return currentIndex < questions.size() - 1;
    }

    public void checkAnswer(int selected) {
        if (selected == getCurrentQuestion().getCorrectAnswer()) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }
}
