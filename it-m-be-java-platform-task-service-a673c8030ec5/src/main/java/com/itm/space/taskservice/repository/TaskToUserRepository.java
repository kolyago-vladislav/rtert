package com.itm.space.taskservice.repository;

import com.itm.space.taskservice.domain.TaskToUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskToUserRepository extends JpaRepository<TaskToUser, UUID> {
}
