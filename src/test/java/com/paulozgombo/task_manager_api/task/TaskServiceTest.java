/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.paulozgombo.task_manager_api.task;

import org.junit.jupiter.api.Test;
import com.paulozgombo.task_manager_api.task.dto.TaskRequest;
import com.paulozgombo.task_manager_api.task.TaskEntity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 *
 * @author paulo
 */
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    
    @InjectMocks
    private TaskService taskService;
    
    @Mock
    private TaskRepository taskRepo;
    
    @Test
    public void shouldReturnTaskListSuccessfully() {
        
        //Arrange
        TaskEntity task1 = TaskEntity.builder()
                            .id(1)
                            .title("A new car")
                            .description("Buy a car")
                            .status(TaskStatus.PENDING)
                            .creatAt(LocalDateTime.now())
                            .build();
        
        TaskEntity task2 = TaskEntity.builder()
                            .id(2)
                            .title("Be good programmer")
                            .description("To Study a lot")
                            .status(TaskStatus.PENDING)
                            .creatAt(LocalDateTime.now())
                            .build();
        
        List<TaskEntity> mockTasks = List.of(task1, task2);
        
        // mocking the repo to return 2 tasks
        when(taskRepo.findAll()).thenReturn(mockTasks);
        
        //Act
        List<TaskEntity> result = taskRepo.findAll();
        
        //Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(taskRepo, times(1)).findAll();
    }
    
    @Test
    public void shouldCreateTaskSuccessfully(){
        
        //Arrange
        TaskRequest request = TaskRequest.builder()
                            .title("A car")
                            .description("Buy a car!")
                            .build();
        
        when(taskRepo.existsByTitle(request.getTitle())).thenReturn(false);
        
        //Act
        taskService.createTask(request);
        
        //Assert
        verify(taskRepo, times(1)).save(any(TaskEntity.class));

    }
    
    @Test
    public void shouldThrowExceptionWhenTitleExists(){
        
        //Arrange
        TaskRequest request = TaskRequest.builder()
                            .title("A car")
                            .description("Buy a car!")
                            .build();
        
        when(taskRepo.existsByTitle(request.getTitle())).thenReturn(true);
        
        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(request);
        });
        
        //Assert
        verify(taskRepo, never()).save(any());

    }
}

