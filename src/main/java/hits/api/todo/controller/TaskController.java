package hits.api.todo.controller;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;
import hits.api.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService service;

    @PostMapping("/create")
    public TaskResponseDTO create(@RequestBody @Valid TaskRequestDTO dto){
        return service.create(dto);
    }
}
