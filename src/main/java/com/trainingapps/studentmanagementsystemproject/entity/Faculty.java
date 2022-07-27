package com.trainingapps.studentmanagementsystemproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer FacultyId;
	@Length(min=6,max=12)
	@NotNull
	@NotBlank
	private String loginId;

	@Length(min=6,max=12)
	@NotNull
	@NotBlank
	private String password;

	@Length(min=3,max=10)
	@NotNull
	@NotBlank
	private String firstName;

	@Length(min=3,max=10)
	@NotNull
	@NotBlank
	private String lastName;

	@Length(min=10,max=10)
	@NotNull
	@NotBlank
	private String mobileNumber;

}
