package com.itm.space.taskservice.service;

import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.api.response.TaskResponse;
import lombok.NonNull;

import java.util.UUID;


public interface TaskService {
    TaskResponse create(@NonNull TaskRequest taskRequest);

    TaskResponse update(@NonNull UUID id, @NonNull TaskRequest request);

}
