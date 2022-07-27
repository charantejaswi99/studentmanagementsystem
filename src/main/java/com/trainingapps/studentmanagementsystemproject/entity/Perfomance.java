package com.trainingapps.studentmanagementsystemproject.entity;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Perfomance {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer perfomanceId;
	
	private HashMap<String, Integer> subjectMap;
	
	private Double totalMarks;
	
	private Integer marksObtained;
	
	private Double totalPercentage;
	
	private String duration;
	
	private String result;
	
	private String modifiedBy;
	
	@JsonIgnore
	@OneToOne(mappedBy = "perfomance")
    private Student student;
}
