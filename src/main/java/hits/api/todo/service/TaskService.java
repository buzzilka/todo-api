package hits.api.todo.service;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(String id, TaskRequestDTO dto);
    void delete(String id);
}
