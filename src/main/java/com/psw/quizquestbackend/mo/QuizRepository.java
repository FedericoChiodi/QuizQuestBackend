package com.psw.quizquestbackend.mo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QuizRepository extends CrudRepository<QuestionEntity, Integer> {

  QuestionEntity findTopByOrderByIdDesc();
  QuestionEntity findById(int id);
  List<QuestionEntity> findAll();
  void deleteById(int id);

  long count();
}
