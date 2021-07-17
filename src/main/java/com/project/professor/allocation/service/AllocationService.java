package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.*;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	
	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
							 CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public void deleteById(Long id) {
		boolean exists = allocationRepository.existsById(id);
		if (exists == true && id != null) {
			allocationRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
	
	public Allocation findById(Long id) {
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		return allocation;
	}
	
	public List<Allocation> findAll() {
		List<Allocation> allocations = allocationRepository.findAll();
		return allocations;
	}
	
	public List<Allocation> findByProfessorId(Long professorId) {
		if (professorId != null) {
			List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
			return allocations;
		} else {
			return null;
		}	
	}
	
	public List<Allocation> findByCourseId(Long courseId) {
		if (courseId != null) {
			List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
			return allocations;
		} else {
			return null;
		}
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		Allocation newAllocation = saveInternal(allocation);
		return newAllocation;
	}
	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		boolean exists = allocationRepository.existsById(allocation.getId());
		if (exists == true && id != null) {
			Allocation newAllocation = saveInternal(allocation);
			return newAllocation;
		} else {
			return null;
			/*retornar nulo, caso id não exista, senão o método update fará um create.*/
		}
	}

	private Allocation saveInternal(Allocation allocation) {
        if (!checkCollision(allocation)) {
            allocation = allocationRepository.save(allocation);

            Professor professor = allocation.getProfessor();
            professor = professorService.findById(professor.getId());
            allocation.setProfessor(professor);

            Course course = allocation.getCourse();
            course = courseService.findById(course.getId());
            allocation.setCourse(course);
            
            return allocation;
        } else {
        	throw new RuntimeException();
        }		 
	}

    boolean checkCollision(Allocation newAllocation) {
        boolean checkCollision = false;

        List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId());

        for (Allocation currentAllocation : currentAllocations) {
        	checkCollision = hasCollision(currentAllocation, newAllocation);
            if (checkCollision = true) {
                break;
            }
        }

        return checkCollision;
    }

    private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
    	boolean hasCollision = false;
    	if (!currentAllocation.getId().equals(newAllocation.getId())
    			&& currentAllocation.getDayofweek() == newAllocation.getDayofweek()
    			&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
    			&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0) {
	    	hasCollision = true;   			 
    	}
    	return hasCollision;
    }
    
    
}
