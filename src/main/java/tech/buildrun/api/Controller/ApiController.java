package tech.buildrun.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.api.model.Task;
import tech.buildrun.api.repository.TaskRepository;

import java.util.List;

@RestController
public class ApiController {

    private final TaskRepository repository;

    public ApiController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<Task>> listTasks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.status(201).body(repository.save(task));
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/tasks")
    public ResponseEntity<Void> clearTasks() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }
}