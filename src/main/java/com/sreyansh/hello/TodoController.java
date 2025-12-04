package com.sreyansh.hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository repo;

    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    // Create a todo: POST /api/todos    body: {"title":"..."}
    @PostMapping
    public Todo create(@RequestBody Map<String, String> body) {
        String title = body.getOrDefault("title", "").trim();
        if (title.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title is required");
        }
        Todo t = new Todo();
        t.setTitle(title);
        return repo.save(t);
    }

    // List all todos: GET /api/todos
    @GetMapping
    public List<Todo> list() {
        return repo.findAll();
    }

    // Mark a todo as done: PATCH /api/todos/{id}/done
    @PatchMapping("/{id}/done")
    public Todo markDone(@PathVariable Long id) {
        Todo t = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        t.setDone(true);
        return repo.save(t);
    }

    // Delete a todo: DELETE /api/todos/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
    }
}