package com.project.professorallocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class AllocationRepositoryTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
	
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Test
	void testCreate () throws ParseException {
		
		Course course = new Course();
		course.setId(1L);
		
		Professor professor = new Professor();
		professor.setId(1L);		
		
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDayofweek(DayOfWeek.MONDAY);
		allocation.setStart(sdf.parse("08:00")); //não sei como aplicar
		allocation.setEnd(sdf.parse("09:00")); //não sei como aplicar
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		
		allocation = allocationRepository.save(allocation);
		
		System.out.println(allocation);
	}
	
	@Test
	void testUpdate () throws ParseException {
		
		Course course = new Course(1L, "Recodev", null);
		course.setId(1L);
		
		Department department = new Department (1L, "departamentoTI", null);
		
		Professor professor = new Professor(1L, "tiago", "111.111.111-11", department, null);
		
		Allocation allocation2 = new Allocation();
		allocation2.setId(1L);
		allocation2.setDayofweek(DayOfWeek.MONDAY);
		allocation2.setStart(sdf.parse("10:00")); //não sei como aplicar
		allocation2.setEnd(sdf.parse("11:00")); //não sei como aplicar
		allocation2.setProfessor(professor);
		allocation2.setCourse(course);
		
		if(allocationRepository.existsById(allocation2.getId()) == true)
		{
			allocation2 = allocationRepository.save(allocation2);
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
		
		System.out.println(allocation);
	}
	
	@Test
	void testFindByProfessorId() {
		Long professorId = 2L;
		
		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
				
		for (Allocation item : allocations) {
			System.out.println(item);
		}
	}
	
	@Test
	void testFindByCourseId() {
		Long courseId = 3L;
		
		List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
		
		for (Allocation item : allocations) {
			System.out.println(item);			
		}
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
