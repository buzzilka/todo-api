package hits.api.todo.component;

import hits.api.todo.entity.TaskEntity;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskStatusScheduler {

    private final TaskRepository repository;

    @PostConstruct
    public void updateTaskStatusesOnStartup(){
        updateTaskStatuses();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTaskStatuses() {
        List<TaskEntity> tasks = repository.findAll();
        Date now = new Date();

        for (TaskEntity task : tasks) {
            if (task.getStatus() == TaskStatus.ACTIVE && task.getDeadline() != null && task.getDeadline().before(now)) {
                task.setStatus(TaskStatus.OVERDUE);
            }
        }

        repository.saveAll(tasks);
    }
}
