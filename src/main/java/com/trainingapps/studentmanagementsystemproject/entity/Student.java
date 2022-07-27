package com.trainingapps.studentmanagementsystemproject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	@Length(min=3,max=10)
	@NotNull
	@NotBlank
	private String firstName;
	
	@Length(min=3,max=10)
	@NotNull
	@NotBlank
	private String lastName;
	
	@Length(min=3,max=10)
	@NotNull
	@NotBlank
	private String phoneNumber;
	
	@Length(min=3,max=20)
	@NotNull
	@NotBlank
	private String address;
	
	@Length(min=1,max=3)
	@NotNull
	@NotBlank
	private String standard;
	
	@Length(min=1,max=3)
	@NotNull
	@NotBlank
	private String setion;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name = "attendenceId" )
	private Attendence attendence;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name = "studentFeeId" )
	private StudentFee studentFee;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name = "performanceId" )
	private Perfomance perfomance;
	
	private String modifiedBy;
			
}
