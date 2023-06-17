package com.itm.space.taskservice.repository;

import com.itm.space.taskservice.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {



}


