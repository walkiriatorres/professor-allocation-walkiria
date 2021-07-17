package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;
	
	CourseService(CourseRepository courseRepository){
		this.courseRepository = courseRepository;
	}
	
	public void deleteById(Long id) {
		boolean exists = courseRepository.existsById(id);
		if (exists == true && id != null) {
		courseRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
	
	public Course findById(Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		return course;
	}
	public List<Course> findAll(String name) {
		if (name != null) {
			List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name);
			return courses;
		} else {
			List<Course> courses = courseRepository.findAll();
			return courses;
		}
	}

	public Course create(Course course) {
		course.setId(null);
		Course newCourse = saveInternal(course);		
		return newCourse;
	}
	
	public Course update(Course course) {
		Long id = course.getId();
		boolean exists = courseRepository.existsById(course.getId());
		if (id != null && exists == true) {
			Course newCourse = saveInternal(course);
			return newCourse;
		} else {
			return null;
			/*retornar nulo, caso id não exista, senão o método update fará um create.*/
		}
	}

	private Course saveInternal(Course course) {
		Course newCourse = courseRepository.save(course);
		return newCourse;
	}

}
