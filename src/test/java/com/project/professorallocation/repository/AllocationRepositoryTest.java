package com.project.professorallocation.repository;

import java.time.DayOfWeek;
import java.util.Date;
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
import com.project.professorallocation.entity.Course;
import com.project.professorallocation.entity.Department;
import com.project.professorallocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class AllocationRepositoryTest {
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Test
	void testCreate () {
		
		Course course = new Course();
		course.setId(1L);
		Professor professor = new Professor();
		professor.setId(1L);		
		
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setDayofweek(DayOfWeek.MONDAY);
		allocation.setStart(null); //não sei como aplicar
		allocation.setEnd(null); //não sei como aplicar
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		
		allocationRepository.save(allocation);
		
		System.out.println(allocation);
	}
	
	@Test
	void testUpdate () {
		
		Course course = new Course();
		course.setId(1L);
		Professor professor = new Professor();
		professor.setId(1L);
		
		Allocation allocation2 = new Allocation();
		allocation2.setId(1L);
		allocation2.setDayofweek(DayOfWeek.MONDAY);
		allocation2.setStart(null); //não sei como aplicar
		allocation2.setEnd(null); //não sei como aplicar
		allocation2.setProfessor(professor);
		allocation2.setCourse(course);
		
		if(allocationRepository.existsById(allocation2.getId()) == true)
		{
		allocationRepository.save(allocation2);
		}
		
		System.out.println(allocation2);
	}
	
	@Test
	void testFindAll () {
		
		List<Allocation> allocations = allocationRepository.findAll();
		
		System.out.println(allocations);
	}
	
	@Test
	void testFindById () {
		
		Long id = 10L;
		
		Optional<Allocation> optional = allocationRepository.findById(id);
		
		Allocation allocation = optional.orElse(null);	
	}
	
	@Test
	void testDeleteById () {
		
		Long id = 2L;
		
		allocationRepository.deleteById(id);
	}
	
	@Test
	void testDeleteAll () {
		//Delete all (CRUD)
		allocationRepository.deleteAllInBatch();
	}
	
	
	
}
