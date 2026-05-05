/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paulozgombo.task_manager_api.task;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
public interface TaskRepository extends JpaRepository <TaskEntity, Integer> {
    
    Optional<TaskEntity> findByTitle(String title);
    boolean existsByTitle(String title);
    boolean existsById(int id);
    
}
