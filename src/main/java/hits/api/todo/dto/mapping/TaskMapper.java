package hits.api.todo.dto.mapping;

import hits.api.todo.dto.request.TaskRequestDTO;
import hits.api.todo.dto.response.TaskResponseDTO;
import hits.api.todo.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity toEntity(TaskRequestDTO dto);

    TaskResponseDTO toDTO(TaskEntity entity);
}
