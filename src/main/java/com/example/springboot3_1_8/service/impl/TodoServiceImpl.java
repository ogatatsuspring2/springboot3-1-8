package com.example.springboot3_1_8.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springboot3_1_8.entity.Todo;
import com.example.springboot3_1_8.form.TodoData;
import com.example.springboot3_1_8.repository.TodoRepository;
import com.example.springboot3_1_8.service.TodoService;
import com.example.springboot3_1_8.support.TodoUtils;

@Service
public class TodoServiceImpl implements TodoService {
  
  
  
  private final TodoRepository todoRepository;
  private final TodoUtils todoUtils;
  
  
  
  public TodoServiceImpl( TodoRepository todoRepository, TodoUtils todoUtils ) {
    this.todoRepository = todoRepository;
    this.todoUtils = todoUtils;
  }
  
  
  
  @Override
  public List<Todo> readAll() {
    return todoRepository.findAll( Sort.by( Sort.Direction.ASC, "id" ) );
  }
  
  
  
  @Override
  public TodoData read( int id ) {
    return todoUtils.Entity2Form( todoRepository.findById( id ).get() );
  }
  
  
  
  @Override
  public boolean create( TodoData todoData, BindingResult result ) {
    System.out.println( todoData.getDone() );
    boolean isValid = todoUtils.isValid( todoData, result );
    if ( !result.hasErrors() && isValid ) {
      Todo todo = todoUtils.Form2Entity( todoData );
      todoRepository.saveAndFlush( todo );
      return true;
    } else {
      return false;
    }
  }
  
  

  @Override
  public boolean update( TodoData todoData, BindingResult result ) {
    boolean isValid = todoUtils.isValid( todoData, result );
    if ( !result.hasErrors() && isValid ) {
      Todo todo = todoUtils.Form2Entity( todoData );
      todoRepository.saveAndFlush( todo );
      return true;
    } else {
      return false;
    }
  }
  
  
  
  @Override
  public boolean delete( TodoData todoData ) {
    Todo todo = todoUtils.Form2Entity( todoData );
    todoRepository.deleteById( todo.getId() );
    return true;
  }
 
  

}
