package com.project.professor.allocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String name;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "department")
	private List<Professor> listProfessors;

	public Department() {
		super();
	}
	
	public Department(Long id, String name, List<Professor> listProfessors) {
		super();
		this.id = id;
		this.name = name;
		this.listProfessors = listProfessors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Professor> getListProfessors() {
		return listProfessors;
	}

	public void setListProfessors(List<Professor> listProfessors) {
		this.listProfessors = listProfessors;
	}
	
}
