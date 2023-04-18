package com.example.todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo request) {
        Todo newTodo = new Todo();
        newTodo.setTitle(request.getTitle());
        newTodo.setDescription(request.getDescription());
        Todo savedTodo = repository.save(newTodo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @Valid @RequestBody Todo updatedTodo) {
        Optional<Todo> optionalTodo = repository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            Todo savedTodo = repository.save(existingTodo);
            return new ResponseEntity<>(savedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
