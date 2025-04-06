package com.taskmanager.api.repository;

import com.taskmanager.api.entity.Task;
import com.taskmanager.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserUsername(String username);
    Optional<Task> findByIdAndUserUsername(Long id, String username);
}
