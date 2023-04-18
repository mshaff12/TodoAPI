package com.example.todoapplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // this interface uses the JPA Repo and doesn't need any methods
}