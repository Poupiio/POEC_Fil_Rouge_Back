package com.example.fil_rouge_back.Model.Repository;

import com.example.fil_rouge_back.Model.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByUserId(Long userId);
}
