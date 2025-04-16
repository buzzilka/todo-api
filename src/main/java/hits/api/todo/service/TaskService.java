package hits.api.todo.service;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
}
