package hits.api.todo.service.impl;

import hits.api.todo.dto.mapping.TaskMapper;
import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;
import hits.api.todo.enums.TaskPriority;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.exeption.AppException;
import hits.api.todo.repository.TaskRepository;
import hits.api.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public TaskResponseDTO create(TaskRequestDTO dto) {
        TaskEntity entity = mapper.toEntity(dto);

        if (entity.getDeadline() != null && entity.getDeadline().before(getCurrentDate())){
            throw new AppException("You cannot create a task with an expired deadline", HttpStatus.BAD_REQUEST);
        }

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
                .orElseThrow(() -> new AppException("Task not found", HttpStatus.NOT_FOUND));


        if(task.getStatus() == TaskStatus.COMPLETED || task.getStatus() == TaskStatus.LATE){
            throw new AppException("You cannot edit a completed task", HttpStatus.BAD_REQUEST);
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

        if (task.getDeadline() != null && getCurrentDate().before(task.getDeadline())){
            task.setStatus(TaskStatus.ACTIVE);
        } else {
            task.setStatus(TaskStatus.OVERDUE);
        }

        return mapper.toDTO(repository.save(task));
    }

    @Override
    public List<TaskResponseDTO> findAll() {
        List<TaskEntity> tasks = repository.findAll();
        return tasks.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(String id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new AppException("Task not found", HttpStatus.NOT_FOUND));

        repository.delete(task);
    }

    @Override
    public void completed(String id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new AppException("Task not found", HttpStatus.NOT_FOUND));

        if (task.getDeadline() == null || getCurrentDate().before(task.getDeadline())) {
            task.setStatus(TaskStatus.COMPLETED);
        } else {
            task.setStatus(TaskStatus.LATE);
        }

        repository.save(task);
    }

    @Override
    public void uncompleted(String id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new AppException("Task not found", HttpStatus.NOT_FOUND));


        if (task.getDeadline() == null || getCurrentDate().before(task.getDeadline())){
            task.setStatus(TaskStatus.ACTIVE);
        } else {
            task.setStatus(TaskStatus.OVERDUE);
        }

        repository.save(task);
    }

    private Date getCurrentDate(){
        return new Date();
    }
}
