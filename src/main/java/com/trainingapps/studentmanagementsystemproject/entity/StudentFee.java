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
public class StudentFee {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentFeeId;
	
	private Double tutionFee;
	
	private Double busFee;
	
	private Double otherFee;
	
	private Double totalFee;
	
	private Double paidFee;
	
	private Double dueFee;
	
	@JsonIgnore
	@OneToOne(mappedBy = "studentFee")
    private Student student;
	
	private String modifiedBy;
}
