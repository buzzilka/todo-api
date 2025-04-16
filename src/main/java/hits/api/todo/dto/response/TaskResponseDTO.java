package hits.api.todo.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class TaskResponseDTO {
    private String id;

    private String title;

    private String description;

    private Date deadline;

    private String status;

    private String priority;

    private Date createDate;

    private Date updateDate;
}
