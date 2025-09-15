package com.example.springboot3_1_8.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot3_1_8.entity.Todo;
import com.example.springboot3_1_8.form.TodoData;
import com.example.springboot3_1_8.service.TodoService;
import com.example.springboot3_1_8.support.TodoUtils;

@Controller
public class TodoController {
  
  
  
  private final TodoService todoService;
  private final TodoUtils todoUtils;
  private final HttpSession session;
  
  
  
  public TodoController( 
      TodoService todoService,
      TodoUtils todoUtils,
      HttpSession session ) {
    
    this.todoService = todoService;
    this.todoUtils = todoUtils;
    this.session = session;
    
  }
  
  
  
  
  @GetMapping( "/todo" )
  public String showTodoList( Model model ) {
    List<Todo> todoList = todoService.readAll();
    model.addAttribute( "todoList", todoList );
    return "todoList";
  }
  
  
  
  @GetMapping( "/todo/{id}" )
  public String showTodo( @PathVariable( name = "id" ) int id, Model model ) {
    TodoData todoData = todoService.read( id );
    model.addAttribute( "todoData", todoData );
    session.setAttribute( "mode", "update" );
    return "todoForm";
    
  }
  
  
  
  @GetMapping( "/todo/create" )
  public String createTodo( Model model ) {
    model.addAttribute( new TodoData() );
    session.setAttribute( "mode", "create" );
    return "todoForm";
  }
  
  
  
  @PostMapping( "/todo/create" )
  public String createTodo( 
    @ModelAttribute @Validated TodoData todoData,
    BindingResult result,
    Model model ) {
    
    if ( todoService.create( todoData, result ) ) {
      return "redirect:/todo";
    } else {
      return "todoForm";
    }
    
  }
  
  
  
  @PostMapping( "/todo/cancel" )
  public String cancel() {
    return "redirect:/todo";
  }
  
  
  
  @PostMapping( "/todo/update" )
  public String updateTodo( 
    @ModelAttribute @Validated TodoData todoData,
    BindingResult result,
    Model model ) {
    
    if ( todoService.update( todoData, result ) ) {
      return "redirect:/todo";
    } else {
      return "todoForm";
    }
  }
  
  
  
  @PostMapping( "/todo/delete" )
  public String deleteTodo( @ModelAttribute @Validated TodoData todoData ) {
    
    if ( todoService.delete( todoData ) ) {
      return "redirect:/todo";
    } else {
      return "todoForm";
    }
  }
  
}
