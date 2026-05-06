/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paulozgombo.task_manager_api.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author paulo
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    
    private String title;
    private String description;
    
}
