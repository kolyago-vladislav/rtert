package com.itm.space.taskservice.mapper;

import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.api.response.TaskResponse;
import com.itm.space.taskservice.domain.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskEntityMapper {

    Task toEntity(TaskRequest source);

    void updateFrom(TaskRequest source, @MappingTarget Task task);

    TaskResponse toTaskResponse(Task task, UUID topicId);
}


