package com.example.springboot3_1_8.support;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.springboot3_1_8.entity.Todo;
import com.example.springboot3_1_8.form.TodoData;



@Service
public class TodoUtils {
  
  
  
  public Todo Form2Entity( TodoData todoData ) {
    Todo todo = new Todo();
    todo.setId( todoData.getId() );
    todo.setTitle( todoData.getTitle() );
    todo.setImportance( todoData.getImportance() );
    todo.setUrgency( todoData.getUrgency() );
    todo.setDone( todoData.getDone() );
    
    SimpleDateFormat sdFormat = new SimpleDateFormat( "yyyy-MM-dd" );
    long ms;
    try {
      ms = sdFormat.parse( todoData.getDeadline() ).getTime();
      todo.setDeadline( new Date( ms ) );
    } catch( ParseException e ) {
      todo.setDeadline( null );
    }
    
    return todo;
    
  }
  
  
  
  public TodoData Entity2Form( Todo todo ) {
    TodoData todoData = new TodoData();
    todoData.setId( todo.getId() );
    todoData.setTitle( todo.getTitle() );
    todoData.setImportance( todo.getImportance() );
    todoData.setUrgency( todo.getUrgency() );
    todoData.setDone( todo.getDone() );
    
    if ( todo.getDeadline() != null ) {
      SimpleDateFormat sdFormat = new SimpleDateFormat( "yyyy-MM-dd" );
      todoData.setDeadline( sdFormat.format( todo.getDeadline() ) );
    } else {
      todoData.setDeadline( null );
    }
    
    return todoData;
    
  }
  
  
  
  public boolean isValid( TodoData todoData, BindingResult result ) {
    
    boolean valid = true;
    
    String title = todoData.getTitle();
    if ( title != null && !title.equals( "" ) ) {
      boolean isAllDoubleSpace = true;
      for ( int i = 0; i < title.length(); i++ ) {
        if ( title.charAt( i ) != '　' ) {
          isAllDoubleSpace = false;
          break;
        }
      }
      if ( isAllDoubleSpace ) {
        FieldError fieldError = new FieldError(
          result.getObjectName(),
          "title",
          "件名が全角スペースです" );
        result.addError( fieldError );
        valid = false;
      }
    }
    
    String deadline = todoData.getDeadline();
    if ( !deadline.equals( "" ) ) {
      LocalDate today = LocalDate.now();
      LocalDate deadlineDate = null;
      try {
        deadlineDate = LocalDate.parse( deadline );
        if ( deadlineDate.isBefore( today ) ) {
          FieldError fieldError = new FieldError(
            result.getObjectName(),
            "deadline",
            "期限を設定する時は本日以降にしてください" );
          result.addError( fieldError );
          valid = false;
        }
      } catch( DateTimeException e ) {
        FieldError fieldError = new FieldError(
          result.getObjectName(),
          "deadline",
          "期限を設定する時はyyyy-mm-dd形式で入力してください" );
        result.addError( fieldError );
        valid = false;
      }
    }
    
    return valid;
    
  }

}
