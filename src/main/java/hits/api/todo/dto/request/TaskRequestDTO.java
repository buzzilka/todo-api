package hits.api.todo.dto.request;

import hits.api.todo.enums.TaskPriority;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDTO {
    @Size(min = 4)
    private String title;

    private String description;

    private Date deadline;

    private TaskPriority priority;
}
