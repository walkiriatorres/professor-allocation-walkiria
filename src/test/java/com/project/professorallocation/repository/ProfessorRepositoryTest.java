package com.project.professorallocation.repository;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	

	@Test
	void testCreate () {
		//Create setando (CRUD)
		Department department = new Department();
		department.setId(2L);
		
		Professor professor = new Professor();
		professor.setName("rafael");
		professor.setCpf("234.567.890-12");
		professor.setDepartment(department);
		professor.setListAllocations(null);
		
		professor.setId(null);
		Professor newProfessor = professorRepository.save(professor);
		Long departmentId = newProfessor.getDepartment().getId();
		
		Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
		newProfessor.setDepartment(newDepartment);
		
		System.out.println(newProfessor);
	}
	@Test
	void testCreate2 () {
		//Create (CRUD)
		Department department = new Department();
		department.setId(1L);
		
		Professor professor2 = new Professor(null, "tiago", "111.111.111-11", department, null);
		
		professor2 = professorRepository.save(professor2);
		
		System.out.println(professor2);
	}
	@Test
	void testUpdate () {
		//Update (CRUD)
		Department department = new Department();
		department.setId(1L);
		
		Professor professor3 = new Professor();
		professor3.setId(1L);
		professor3.setName("thiago");
		professor3.setCpf("111.111.111-11");
		professor3.setDepartment(department);
		professor3.setListAllocations(null);
		
		if(professorRepository.existsById(professor3.getId()) == true)
		{
		professor3 = professorRepository.save(professor3);
		}		
			
		/* OU
		* boolean exist =	professorRepository.existsById(professor3.getId());
		if(exist == true){
		professorRepository.save(professor3);
		} */
			
		System.out.println(professor3);
		
	}
	
	@Test
	void testFindAll () {
		//FindAll READ (CRUD)
		List<Professor> professors = professorRepository.findAll();
		
		for (Professor item : professors) {
			System.out.println(item);
		}
	}
	
	@Test
	void testFindById () {
		//FindByID READ (CRUD)
		Long id = 10L;
		
		Professor professor = professorRepository.findById(id).orElse(null);
		
		System.out.println(professor);
	}
	
	@Test
	void testDeleteById () {
		//Delete (CRUD)
		Long id = 2L;
		
		professorRepository.deleteById(id);
	
	}
	
	@Test
	void testDeleteAll () {
		//Delete all (CRUD)
		professorRepository.deleteAllInBatch();
	}
	
	
}