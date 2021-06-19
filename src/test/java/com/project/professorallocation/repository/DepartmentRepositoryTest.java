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
import com.project.professorallocation.entity.Allocation;
import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

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
		department.setId(1L);
		
		departmentRepository.save(department);
		
		System.out.println(department);
	}
	
	@Test
	void testCreate2 () {
		Department department2 = new Department(1L, "DepartTecnologia", null);
		
		departmentRepository.save(department2);
		
		System.out.println(department2);
		
	}
	
	@Test
	void testUpdate () {
		
		Department department3 = new Department();
		department3.setId(1L);
		department3.setName("DepartTecnologia");
		department3.setProfessor(null);
		
		if(departmentRepository.existsById(department3.getId()) == true)
		{
		departmentRepository.save(department3);
		}
		
		System.out.println(department3);
	}
	
	@Test
	void testFindAll () {
		
		List<Department> departments = departmentRepository.findAll();
		
		System.out.println(departments);
	}
	
	@Test
	void testFindById () {
		
		Long id = 10L;
		
		Optional<Department> optional = departmentRepository.findById(id);
		
		Department depto = optional.orElse(null);	
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
