package com.example.springboot3_1_8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot3_1_8.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}
