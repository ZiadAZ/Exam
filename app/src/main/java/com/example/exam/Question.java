package com.example.exam;


public class Question {
    private int tQuestion;
    private boolean answer;
    private int degree;
    private char cat;


    public int getDegree() {
        return degree;
    }

    public char getCat() {
        return cat;
    }

    public Question(int tQuestion, boolean answer, int degree, char cat) {
        this.tQuestion = tQuestion;
        this.answer = answer;
        this.degree = degree;
        this.cat = cat;
    }

    public int gettQuestion() {
        return tQuestion;
    }

    public boolean isAnswer() {
        return answer;
    }


}
