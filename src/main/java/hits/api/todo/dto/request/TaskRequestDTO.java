package hits.api.todo.dto.request;

import hits.api.todo.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDTO {
    @NotBlank
    @Size(min = 4)
    private String title;

    private String description;

    private Date deadline;

    @NotNull
    private TaskPriority priority;
}
