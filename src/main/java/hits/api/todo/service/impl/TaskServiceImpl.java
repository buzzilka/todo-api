package hits.api.todo.service.impl;

import hits.api.todo.dto.mapping.TaskMapper;
import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;
import hits.api.todo.enums.TaskPriority;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.repository.TaskRepository;
import hits.api.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public TaskResponseDTO create(TaskRequestDTO dto) {
        TaskEntity entity = mapper.toEntity(dto);

        if (entity.getDescription().isBlank()){
            entity.setDescription(null);
        }

        if (entity.getPriority() == null){
            entity.setPriority(TaskPriority.MEDIUM);
        }

        entity.setStatus(TaskStatus.ACTIVE);

        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TaskResponseDTO update(String id, TaskRequestDTO dto) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if(task.getStatus() == TaskStatus.COMPLETED || task.getStatus() == TaskStatus.LATE){
            throw new IllegalStateException("You cannot edit a completed task");
        }

        task.setTitle(dto.getTitle());

        if (dto.getDescription() != null && !dto.getDescription().isBlank()){
            task.setDescription(dto.getDescription());
        }

        if (dto.getDeadline() != null){
            task.setDeadline(dto.getDeadline());
        }

        if (dto.getPriority() != null){
            task.setPriority(dto.getPriority());
        }

        if (task.getDeadline() != null && task.getDeadline().before(new Date())){
            task.setStatus(TaskStatus.OVERDUE);
        }

        return mapper.toDTO(repository.save(task));
    }

    @Override
    public void delete(String id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        repository.delete(task);
    }
}
