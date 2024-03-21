package com.example.fil_rouge_back.Model.Repository;

import com.example.fil_rouge_back.Model.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
