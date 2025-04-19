package hits.api.todo.controller;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.enums.TaskPriority;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService service;

    @PostMapping("/create")
    public TaskResponseDTO create(@RequestBody @Valid TaskRequestDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable String id,
                                  @RequestBody @Valid TaskRequestDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/completed/{id}")
    public ResponseEntity<Void> completed(@PathVariable String id){
        service.completed(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uncompleted/{id}")
    public ResponseEntity<Void> uncompleted(@PathVariable String id){
        service.uncompleted(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<TaskResponseDTO> allTasks(
            @RequestParam(required = false, defaultValue = "desc") String sortByCreatedAt,
            @RequestParam(required = false) TaskPriority filterByPriority,
            @RequestParam(required = false)TaskStatus filterByStatus
            ){
        return service.findAll(sortByCreatedAt, filterByPriority, filterByStatus);
    }
}
