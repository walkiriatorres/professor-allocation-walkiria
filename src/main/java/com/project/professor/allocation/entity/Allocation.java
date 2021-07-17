package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private DayOfWeek dayofweek;
	
	@JsonFormat(pattern = "HH:mmZ") // Exemplo: 14:00-0300
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@Column(nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date start;
	
	@JsonFormat(pattern = "HH:mmZ") // Exemplo: 14:00-0300
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@Column(nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date end;
	
	@JsonIgnoreProperties({"listAllocations"})
	@ManyToOne(optional = false)
	private Professor professor;
	
	@JsonIgnoreProperties({"listAllocations"})
	@ManyToOne(optional = false)
	private Course course;

	public Allocation() {
		super();
	}
	
	public Allocation(Long id, DayOfWeek dayofweek, Date start, Date end, Professor professor, Course course) {
		super();
		this.id = id;
		this.dayofweek = dayofweek;
		this.start = start;
		this.end = end;
		this.professor = professor;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(DayOfWeek dayofweek) {
		this.dayofweek = dayofweek;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
