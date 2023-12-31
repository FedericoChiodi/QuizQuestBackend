package com.psw.quizquestbackend.mo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "question", schema = "QuizQuest")
public class QuestionEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "question_text", nullable = false, length = 255)
    private String questionText;
    @Basic
    @Column(name = "answer_1", nullable = false, length = 255)
    private String answer1;
    @Basic
    @Column(name = "answer_2", nullable = false, length = 255)
    private String answer2;
    @Basic
    @Column(name = "correct_answer", nullable = false)
    private Integer correctAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(questionText, that.questionText) && Objects.equals(answer1, that.answer1) && Objects.equals(answer2, that.answer2) && Objects.equals(correctAnswer, that.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, answer1, answer2, correctAnswer);
    }
}
