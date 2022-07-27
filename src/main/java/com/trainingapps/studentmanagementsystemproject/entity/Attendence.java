package com.trainingapps.studentmanagementsystemproject.entity;

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
public class Attendence {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer attendenceId;
	
    private Integer totalClasses;
    
    private Integer attendedClasses;
    
    private Double attendencePercentage;
    
    private String modifiedBy;
    
    @JsonIgnore
    @OneToOne(mappedBy = "attendence")
    private Student student;
}
