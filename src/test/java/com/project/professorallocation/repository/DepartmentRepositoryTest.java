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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	

	@Test
	void testCreate () {
		Department department = new Department();
		department.setId(null);
		department.setName("DepartTecnologia");
		department.setListProfessors(null);
		
		department = departmentRepository.save(department);
		
		System.out.println(department);
	}
	
	@Test
	void testCreate2 () {
		Department department2 = new Department(null, "DepartTecnologia", null);
		
		department2 = departmentRepository.save(department2);
		
		System.out.println(department2);
		
	}
	
	@Test
	void testUpdate () {
		
		Department department3 = new Department();
		department3.setId(1L);
		department3.setName("DepartTecnologia");
		department3.setListProfessors(null);
		
		if(departmentRepository.existsById(department3.getId()) == true)
		{
			department3 = departmentRepository.save(department3);
		}
		
		System.out.println(department3);
	}
	
	@Test
	void testFindAll () {
		
		List<Department> departments = departmentRepository.findAll();
		
		for (Department item : departments) {
			System.out.println(item);
		}
	}
	
	@Test
	void testFindById () {
		
		Long id = 10L;
		
		Department department = departmentRepository.findById(id).orElse(null);
		
		System.out.println(department);
	}
	
	@Test
	void testDeleteById () {
		
		Long id = 2L;
		
		departmentRepository.deleteById(id);
	}
	
	@Test
	void testDeleteAll () {
		//Delete all (CRUD)
		departmentRepository.deleteAllInBatch();
	}
	
}
