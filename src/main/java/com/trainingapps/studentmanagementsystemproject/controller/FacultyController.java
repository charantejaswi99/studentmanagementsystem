package com.trainingapps.studentmanagementsystemproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trainingapps.studentmanagementsystemproject.entity.Faculty;
import com.trainingapps.studentmanagementsystemproject.exceptions.FacultyException;
import com.trainingapps.studentmanagementsystemproject.service.FacultyService;

@RequestMapping("/faculty")
@RestController
public class FacultyController {

	@Autowired
	FacultyService facultyService;
//	http://localhost:8083/faculty/login/Krish336/kalyan@336
	@PostMapping("/login/{id}/{pass}")
	public ResponseEntity<String> Login(@PathVariable(value = "id")String loginId,@PathVariable(value = "pass")String password) {
		try {
			System.out.println(loginId);
			System.out.println(password);
		String res = facultyService.login(loginId, password);
			return new ResponseEntity<>(res,HttpStatus.OK);
		}catch(FacultyException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get StudentData", e);
		}
	}
//	http://localhost:8083/faculty/addfaculty
	@PostMapping("/addfaculty")
	public ResponseEntity<Faculty> addFaculty(@Valid @RequestBody Faculty faculty) {
		try {
			System.out.println(faculty);
		 Faculty f= facultyService.addFaculty(faculty);
		 
			return new ResponseEntity<>(f,HttpStatus.OK);
		}catch(FacultyException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add FacultyData", e);
		}
	}

//	http://localhost:8083/faculty/updatefacultybyid/5
	@PutMapping("/updatefacultybyid/{id}")
	public ResponseEntity<Faculty> updateFaculty(@Valid @PathVariable(value = "id") Integer facultyId, @RequestBody Faculty faculty,BindingResult bindingResult) {
		try{
			if(bindingResult.hasErrors()) {
				//System.out.println(bindingResult.getAllErrors().toString());
				throw new FacultyException(bindingResult.getAllErrors().toString());
			}
			Faculty f = facultyService.updateFacultyById(facultyId, faculty);
			return new ResponseEntity<>(f,HttpStatus.OK);
		}catch(FacultyException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	//	http://localhost:8083/faculty/delete/9
		@DeleteMapping("/delete/{facultyId}")
		public String deleteFaculty(@PathVariable int facultyId) {
			try{
				facultyService.deleteFaculty(facultyId);
				return facultyId + " is deleted";
			}catch(FacultyException e) {
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete faculty", e);
			}
		}
//		http://localhost:8083/faculty/facultylist
		@GetMapping("/facultylist")
		public ResponseEntity<List<Faculty>> getFacultyList(){
			try{
				List<Faculty> facultyList=facultyService.getFacultyList();
				return new ResponseEntity<>(facultyList,HttpStatus.OK);	
			}catch(FacultyException e) {
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get facultyList", e);
			}
		}
}