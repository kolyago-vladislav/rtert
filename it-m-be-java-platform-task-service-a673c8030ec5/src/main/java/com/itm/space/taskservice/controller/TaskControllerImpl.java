package com.itm.space.taskservice.controller;

import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.api.response.TaskResponse;
import com.itm.space.taskservice.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itm.space.taskservice.util.TaskControllerConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(TaskControllerConst.TASK_PATH)
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;

    @Override
    public TaskResponse create(@RequestBody @Valid TaskRequest request) {
        return taskService.create(request);
    }

    @Override
    public TaskResponse update(@PathVariable UUID id, @RequestBody @Valid TaskRequest request) {
        return taskService.update(id, request);
    }
}
