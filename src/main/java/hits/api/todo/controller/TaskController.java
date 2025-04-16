package hits.api.todo.controller;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
