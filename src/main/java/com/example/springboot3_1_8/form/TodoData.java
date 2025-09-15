package com.example.springboot3_1_8.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;



@Data
public class TodoData {
  
  private Integer id;
  
  @NotBlank( message = "件名を入力してくださ" )
  private String title;
  
  @NotNull( message = "重要度を先手区してください" )
  private Integer importance;
  
  @Min( value = 0, message = "緊急度を選択してください" )
  private Integer urgency;
  
  private String deadline;
  
  private String done;
  
  
  
}
