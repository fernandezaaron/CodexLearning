package com.codex.learning.utility.decisiontree;

/**
 * This class holds both a question and it's information gain.
 * @author 756887
 *
 */
public class QuestionGainPair{


    private Question question;
    private Double gain;

    public QuestionGainPair(Question question, Double gain){
        this.question = question;
        this.gain = gain;
    }

    public QuestionGainPair(){
        question = new Question();
        gain = 0.0;
    }

    public Question getQuestion() {
        return question;
    }

    public Double getGain() {
        return gain;
    }

}
