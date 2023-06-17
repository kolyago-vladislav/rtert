package com.itm.space.taskservice.controller;

import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.api.response.TaskResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
@Tag(name = "Task Controller", description = "CRUD операции с задачами")
public interface TaskController {

    @Operation(summary = "Create task", description = "Создание новой задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача создана"),
            @ApiResponse(responseCode = "403", description = "В доступе отказано"),
            @ApiResponse(responseCode = "500", description = "Сервер не может создать задачу")
    })
    @PostMapping
    @Secured("ROLE_MODERATOR")
    TaskResponse create(@Parameter(description = "Запрос на создание задачи")
                         @Valid @RequestBody TaskRequest taskRequest);

    @Operation(summary = "update", description = "Обновление задачи по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Cервер не смог понять запрос"),
            @ApiResponse(responseCode = "500", description = "Cервер не может корректно обработать запрос")
    })
    @PutMapping("/{id}")
    @Secured("ROLE_MODERATOR")
    TaskResponse update(@PathVariable("id") UUID id, @Valid @RequestBody TaskRequest taskRequest);
}
