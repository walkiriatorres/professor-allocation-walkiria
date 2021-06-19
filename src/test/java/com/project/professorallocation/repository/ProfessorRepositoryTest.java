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

public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;
	

	@Test
	void testCreate () {
		//Create setando (CRUD)
		Department department = new Department();
		department.setId(1L);
		
		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("thiago");
		professor.setCpf("111.111.111-11");
		professor.setDepartament(department);
		
		professorRepository.save(professor);
		
		System.out.println(professor);
	}
	@Test
	void testCreate2 () {
		//Create (CRUD)
		Department department = new Department();
		department.setId(1L);
		
		Professor professor2 = new Professor(1L, "tiago", "111.111.111-11", department, null);
		
		professorRepository.save(professor2);
		
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
		professor3.setDepartament(department);
		professor3.setAllocations(null);
		
		if(professorRepository.existsById(professor3.getId()) == true)
		{
		professorRepository.save(professor3);
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
		
		System.out.println(professors);
	}
	
	@Test
	void testFindById () {
		//FindByID READ (CRUD)
		Long id = 10L;
		
		Optional<Professor> optional = professorRepository.findById(id);
		
		Professor p = optional.orElse(null);
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