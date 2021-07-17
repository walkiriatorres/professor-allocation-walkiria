package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Department"})
@RestController
@RequestMapping(path = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@ApiResponse(code = 204 , message = "NO_CONTENT")
	@ApiOperation(value = "Delete a department")
	@DeleteMapping(path = "/{department_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "department_id")Long id) {
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ApiResponse(code = 204 , message = "NO_CONTENT")
	@ApiOperation(value = "Delete all departments")
	@DeleteMapping (path = "/")
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ApiResponse(code = 200, message = "OK")
	@ApiOperation(value = "Find a department")
	@GetMapping
	//@Get
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "partName", required = false) String partName){
			List<Department> departments = departmentService.findAll(partName);
			return new ResponseEntity<>(departments, HttpStatus.OK);			
	}
	
	@ApiResponses({
		@ApiResponse(code = 404, message = "NOT_FOUND"),
		@ApiResponse(code = 200, message = "OK")
	})
	@ApiOperation(value = "Find a department by Id")
	@GetMapping(path = "/{department_id}") //GET /departments/1
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id){
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(department, HttpStatus.OK);
		}
	}
	
	@ApiResponses({
		@ApiResponse(code = 201, message = "CREATED"),
		@ApiResponse(code = 200, message = "BAD_REQUEST"),		
	})
	@ApiOperation(value = "Create a department")
	@PostMapping (path = "/")
	public ResponseEntity<Department> create(@RequestBody Department department) {
		try {
			Department newDepartment = departmentService.create(department);
			return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiResponses ({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "NOT FOUND"),
		@ApiResponse(code = 200, message = "BAD_REQUEST"),
	})
	@ApiOperation(value = "Update a department")
	@PutMapping (path = "/{department_id}")
	public ResponseEntity<Department> update(@PathVariable(name = "department_id")Long id, @RequestBody Department department) {
		department.setId(id);
		try {
			Department newDepartment = departmentService.update(department);
			if (newDepartment == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
			return new ResponseEntity<>(newDepartment, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
	}


}
