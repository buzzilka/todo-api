package hits.api.todo.component;

import hits.api.todo.entity.TaskEntity;
import hits.api.todo.enums.TaskStatus;
import hits.api.todo.repository.TaskRepository;
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

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTaskStatuses() {
        List<TaskEntity> tasks = repository.findAll();
        Date now = new Date();

        for (TaskEntity task : tasks) {
            if (task.getStatus() == TaskStatus.COMPLETED || task.getStatus() == TaskStatus.LATE) {
                if (now.after(task.getDeadline())) {
                    task.setStatus(TaskStatus.OVERDUE);
                } else {
                    task.setStatus(TaskStatus.ACTIVE);
                }
            } else {
                task.setStatus(TaskStatus.ACTIVE);
            }
        }

        repository.saveAll(tasks);
    }
}
