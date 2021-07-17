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
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;
	

	@Test
	void testCreate () {
		Course course = new Course();
		course.setId(null);
		course.setName("Recodev5");
		course.setListAllocations(null);
		
		course = courseRepository.save(course);
		
		System.out.println(course);
	}
	
	@Test
	void testCreate2 () {
		Course course2 = new Course(null, "Recodev5", null);
		
		course2 = courseRepository.save(course2);
		
		System.out.println(course2);
		
	}
	
	@Test
	void testUpdate () {
		
		Course course3 = new Course();
		course3.setId(1L);
		course3.setName("Recodev6");
		course3.setListAllocations(null);
		
		if(courseRepository.existsById(course3.getId()) == true)
		{
		course3 = courseRepository.save(course3);
		}
		
		System.out.println(course3);
	}
	
	@Test
	void testFindAll () {
		
		List<Course> courses = courseRepository.findAll();
		
		System.out.println(courses);
		
		courses.forEach(System.out::println);
		
		for (Course item : courses) {
			System.out.println(item);
		}		
	}
	
	@Test
	void testFindById () {
		
		Long id = 10L;	
		
		Course course = courseRepository.findById(id).orElse(null);
		
		System.out.println(course);
	}
	
	@Test
	void testDeleteById () {
		
		Long id = 2L;
		
		courseRepository.deleteById(id);
	}
	
	@Test
	void testDeleteAll () {
		//Delete all (CRUD)
		courseRepository.deleteAllInBatch();
	}
	
}
