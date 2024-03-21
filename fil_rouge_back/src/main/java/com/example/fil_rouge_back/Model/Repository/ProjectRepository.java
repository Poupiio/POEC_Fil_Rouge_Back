package com.example.fil_rouge_back.Model.Repository;

import com.example.fil_rouge_back.Model.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}