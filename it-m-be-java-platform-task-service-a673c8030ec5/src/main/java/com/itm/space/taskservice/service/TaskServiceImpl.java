package com.itm.space.taskservice.service;

import com.itm.space.taskservice.api.request.TaskRequest;
import com.itm.space.taskservice.api.response.TaskResponse;
import com.itm.space.taskservice.domain.Task;
import com.itm.space.taskservice.domain.TaskToTopic;
import com.itm.space.taskservice.exception.NotFoundException;
import com.itm.space.taskservice.mapper.TaskEntityMapper;
import com.itm.space.taskservice.repository.TaskRepository;
import com.itm.space.taskservice.repository.TaskToTopicRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskToTopicRepository taskToTopicRepository;
    private final TaskEntityMapper mapper;

    @Override
    @Transactional
    public TaskResponse create(@NonNull TaskRequest taskRequest) {
        UUID topicId = taskRequest.getTopicId();
        Task savedTask = taskRepository.save(mapper.toEntity(taskRequest));
        TaskToTopic taskToTopic = new TaskToTopic(savedTask.getId(), topicId);
        taskToTopicRepository.save(taskToTopic);
        return mapper.toTaskResponse(savedTask, topicId);
    }


    @Override
    @Transactional
    public TaskResponse update(@NonNull UUID id, @NonNull TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Задача с идентификатором " + id + " не найдена"));
        mapper.updateFrom(taskRequest, task);
        Task updatedTask = taskRepository.save(task);
        TaskToTopic updatedTaskToTopic = taskToTopicRepository.findByTaskId(id);
        updatedTaskToTopic.setTopicId(taskRequest.getTopicId());
        taskToTopicRepository.save(updatedTaskToTopic);
        return mapper.toTaskResponse(updatedTask, taskRequest.getTopicId());
    }

}