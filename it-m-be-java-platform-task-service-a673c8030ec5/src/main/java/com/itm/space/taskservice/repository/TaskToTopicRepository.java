package com.itm.space.taskservice.repository;

import com.itm.space.taskservice.domain.TaskToTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskToTopicRepository extends JpaRepository<TaskToTopic, UUID> {
    TaskToTopic findByTaskId(UUID taskId);
}
