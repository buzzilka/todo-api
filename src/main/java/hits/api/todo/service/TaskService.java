package hits.api.todo.service;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(String id, TaskRequestDTO dto);
    List<TaskResponseDTO> findAll();
    void delete(String id);
    void completed(String id);
    void uncompleted(String id);
}
