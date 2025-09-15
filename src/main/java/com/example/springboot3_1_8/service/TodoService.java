package com.example.springboot3_1_8.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.example.springboot3_1_8.entity.Todo;
import com.example.springboot3_1_8.form.TodoData;

public interface TodoService {
  
  List<Todo> readAll();
  TodoData read( int id );
  boolean create( TodoData todoData, BindingResult result );
  boolean update( TodoData todoData, BindingResult result );
  boolean delete( TodoData todoData );

}
