package com.project.professorallocation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.professorallocation.service.DepartmentService;

@RestController
public class DepartmentController {
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	

	

}
