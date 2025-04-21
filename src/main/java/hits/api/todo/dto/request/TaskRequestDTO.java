package hits.api.todo.dto.request;

import hits.api.todo.enums.TaskPriority;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDTO {
    @NotNull
    @Size(min = 4, max = 255, message = "Title length cannot be less than 4 and more than 255 characters")
    private String title;

    @Size(max = 255, message = "Description length cannot be more than 255 characters")
    private String description;

    private Date deadline;

    private TaskPriority priority;
}
