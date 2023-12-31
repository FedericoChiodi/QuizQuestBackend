package com.psw.quizquestbackend.controller;

import com.psw.quizquestbackend.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/QuizQuest")
public class quizController {

  @Autowired
  private QuizRepository quizRepository;

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<QuestionEntity> getQuestions() {
    return quizRepository.findAll();
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionEntity> getQuestion(@PathVariable int id) {
    QuestionEntity question = quizRepository.findById(id);
    if (question != null) {
      return ResponseEntity.ok(question);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions", method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public QuestionEntity addQuestion(@RequestBody QuestionEntity question){
    QuestionEntity lastQuestionID = quizRepository.findTopByOrderByIdDesc();
    int id = 1;
    if(lastQuestionID != null){
      id = lastQuestionID.getId()+1;
    }
    question.setId(id);

    quizRepository.save(question);
    return question;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions/{id}", method = RequestMethod.PUT,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionEntity> updateQuestion(@PathVariable int id, @RequestBody QuestionEntity requestQuestion) {
    QuestionEntity existingQuestion = quizRepository.findById(id);

    if (existingQuestion != null) {
      existingQuestion.setQuestionText(requestQuestion.getQuestionText());
      existingQuestion.setAnswer1(requestQuestion.getAnswer1());
      existingQuestion.setAnswer2(requestQuestion.getAnswer2());
      existingQuestion.setCorrectAnswer(requestQuestion.getCorrectAnswer());

      quizRepository.save(existingQuestion);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions/{id}", method = RequestMethod.DELETE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
    QuestionEntity question = quizRepository.findById(id);
    if (question != null) {
      quizRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "/questions/count", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public long countQuestions(){
    return quizRepository.count();
  }

}
