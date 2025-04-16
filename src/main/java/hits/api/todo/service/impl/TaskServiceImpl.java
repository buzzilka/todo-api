package hits.api.todo.service.impl;

import hits.api.todo.dto.mapping.TaskRequestMapper;
import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;
import hits.api.todo.enums.TaskPriority;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.repository.TaskRepository;
import hits.api.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskRequestMapper mapper;

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
}
