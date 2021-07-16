package com.project.professorallocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;
	
	@JsonIgnoreProperties({"listprofessors"})
	@ManyToOne(optional = false)
	private Department department;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "professor")
	private List<Allocation> listAllocations;

	public Professor() {
		super();
	}
	
	public Professor(Long id, String name, String cpf, Department department, List<Allocation> listallocations) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.department = department;
		this.listAllocations = listallocations;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Allocation> getListAllocations() {
		return listAllocations;
	}

	public void setListAllocations(List<Allocation> listAllocations) {
		this.listAllocations = listAllocations;
	}

}
