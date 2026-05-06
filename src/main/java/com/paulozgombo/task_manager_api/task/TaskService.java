/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paulozgombo.task_manager_api.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.paulozgombo.task_manager_api.task.dto.TaskRequest;
import com.paulozgombo.task_manager_api.task.TaskEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
/**
 *
 * @author paulo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepo;
    
    @Transactional
    public void createTask( final TaskRequest request){
        log.info("Attempting to create task: {}", request.getTitle());
        requestingForTaskCreation(request.getTitle(), request.getDescription());
   }
    
    @Transactional
    public void update(TaskRequest request){
        log.info("Attempting to update task and description: {}", request.getTitle(), request.getDescription());
        requestingForUpdate(request.getTitle(), request.getDescription()); 
    }
    
    private void requestingForTaskCreation(final String title, final String description){
        if(taskRepo.existsByTitle(title)){
            throw new IllegalArgumentException("This task already exists! " + title);
        }
        
        TaskEntity newTask = TaskEntity.builder()
                            .title(title)
                            .description(description)
                            .status(TaskStatus.PENDING)
                            .build();
            
        taskRepo.save(newTask);    
    }
    
    private void requestingForUpdate(String title, String description) {
        taskRepo.findByTitle(title)
            .map(existingTask -> {
                // Update the fields
                existingTask.setTitle(title);
                existingTask.setDescription(description);
                // Return the modified entity to the Optional
                return taskRepo.save(existingTask);
            })
            .orElseThrow(() -> new EntityNotFoundException("Task not found with title: " + title));
    }
    
   /* public List <TaskEntity> findTasks(){
        return taskRepo.findAll().stream()
                .map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus()))
                .toList();
    }*/
    
    public List <TaskEntity> findTasks(){
        return taskRepo.findAll();
    }

   
}
