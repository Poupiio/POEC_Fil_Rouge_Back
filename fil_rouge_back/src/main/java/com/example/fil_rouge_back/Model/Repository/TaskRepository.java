package com.example.fil_rouge_back.Model.Repository;

import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
=======
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
}
>>>>>>> 1db4a4e4505af9af16ae92449664ef2938f1a82e
