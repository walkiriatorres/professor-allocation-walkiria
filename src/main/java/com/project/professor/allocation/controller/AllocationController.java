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

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.AllocationService;
import com.project.professor.allocation.service.CourseService;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Allocation"})
@RestController
@RequestMapping(path = "/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {
	private final AllocationService allocationService;
	
	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
	
	@ApiResponse(code = 200, message = "OK")
	@ApiOperation(value = "Find a allocation")
	@GetMapping
	//@Get
	public ResponseEntity<List<Allocation>> findAll(){
			List<Allocation> allocations = allocationService.findAll();
			return new ResponseEntity<>(allocations, HttpStatus.OK);			
	}
	
	@ApiResponses({
		@ApiResponse(code = 404, message = "NOT_FOUND"),
		@ApiResponse(code = 200, message = "OK")
	})
	@ApiOperation(value = "Find a allocation by Id")
	@GetMapping(path = "/{allocation_id}") //GET /professors/1
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id){
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@ApiResponse(code = 204 , message = "NO_CONTENT")
	@ApiOperation(value = "Delete a allocation")
	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "allocation_id")Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiResponse(code = 204 , message = "NO_CONTENT")
	@ApiOperation(value = "Delete all allocations")
	@DeleteMapping (path = "/")
	public ResponseEntity<Void> deleteAll() {
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiResponses({
		@ApiResponse(code = 201, message = "CREATED"),
		@ApiResponse(code = 200, message = "BAD_REQUEST"),		
	})
	@ApiOperation(value = "Create a allocation")
	@PostMapping (path = "/")
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		try {
			Allocation newAllocation = allocationService.create(allocation);
			return new ResponseEntity<>(newAllocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiResponses ({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "NOT FOUND"),
		@ApiResponse(code = 200, message = "BAD_REQUEST"),
	})
	@ApiOperation(value = "Update a allocation")
	@PutMapping (path = "/{allocation_id}")
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id")Long id, @RequestBody Allocation allocation) {
		allocation.setId(id);
		try {
			Allocation newAllocation = allocationService.update(allocation);
			if (newAllocation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
			return new ResponseEntity<>(newAllocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
	}



}
