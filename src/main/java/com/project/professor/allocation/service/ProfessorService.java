package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final DepartmentRepository departmentRepository;
	
	public ProfessorService (ProfessorRepository professorRepository, DepartmentRepository departmentRepository) {
		this.professorRepository = professorRepository;
		this.departmentRepository = departmentRepository;
	}

	public void deleteById(Long id) {
		boolean exists = professorRepository.existsById(id);
		if (exists == true && id != null) {
		professorRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}
	
	public Professor findById(Long id) {		
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}
	
	public List<Professor> findAll(String name) {
		if (name != null) {
			List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);
			return professors;
		} else {
			List<Professor> professors = professorRepository.findAll();
			return professors;
		}
	}
	
	public List<Professor> findByDepartmentId (Long departmentId) {
		List<Professor> professors = professorRepository.findByDepartmentId(departmentId);	
		return professors;	
	}
	
	public Professor create(Professor professor) {
		professor.setId(null); //garante que será criado um novo professor
		Professor newProfessor = saveInternal(professor);
		return newProfessor;
	}
	
	public Professor update(Professor professor) {
		Long id = professor.getId();
		boolean exists = professorRepository.existsById(professor.getId());
		if (exists == true && id != null ) {
			Professor newProfessor = saveInternal(professor);
			return newProfessor;
		} else {
			return null;
			/*retornar nulo, caso id não exista, senão o método update fará um create.*/
		}
	}

	private Professor saveInternal(Professor professor) {
		Professor newProfessor = professorRepository.save(professor);
		
		Long departmentId = newProfessor.getDepartment().getId();
		Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
		newProfessor.setDepartment(newDepartment);
		
		return newProfessor;
	}
}
