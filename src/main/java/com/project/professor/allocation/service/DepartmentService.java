package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public void deleteById(Long id) {
		boolean exists = departmentRepository.existsById(id);
		if (exists == true && id != null) {
		departmentRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

	public Department findById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return department;
	}
	
	public List<Department> findAll(String name) {
		if (name != null) {
			List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
			return departments;
		} else {
			List<Department> departments = departmentRepository.findAll();
			return departments;
			}
	}
	
	public Department create (Department department) {
		department.setId(null);
		Department newDepartment = saveInternal(department);		
		return newDepartment;
	}
	
	public Department update (Department department) {
		Long id = department.getId();
		boolean exists = departmentRepository.existsById(department.getId());
		if (id != null && exists == true) {
			Department newDepartment = saveInternal(department);
			return newDepartment;
		} else {
			return null;
			/*retornar nulo, caso id não exista, senão o método update fará um create.*/
		}
	}

	private Department saveInternal (Department department) {
		Department newDepartment = departmentRepository.save(department);
		return newDepartment;
	}
}
