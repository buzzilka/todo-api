package hits.api.todo.service;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.enums.TaskPriority;
import hits.api.todo.enums.TaskStatus;

import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);

    TaskResponseDTO update(String id, TaskRequestDTO dto);

    List<TaskResponseDTO> findAll(String sortByCreatedAt,
                                  TaskPriority filterByPriority,
                                  TaskStatus filterByStatus);

    void delete(String id);

    void completed(String id);

    void uncompleted(String id);
}
