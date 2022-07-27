package com.trainingapps.studentmanagementsystemproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.trainingapps.studentmanagementsystemproject.entity.Attendence;
import com.trainingapps.studentmanagementsystemproject.exceptions.AttendenceException;
import com.trainingapps.studentmanagementsystemproject.service.AttendenceService;

@RequestMapping("/attendence")
@RestController
public class AttendenceController {
	@Autowired
	AttendenceService attendenceService;

//	http://localhost:8083/attendence/getbyid/10
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Attendence> getAttendenceById(@PathVariable(value = "id") int attendenceId) {
		try{
			Attendence attendence= attendenceService.getAttendenceById(attendenceId);
			return new ResponseEntity<>(attendence,HttpStatus.OK);
		}catch(AttendenceException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get attendenceData with id  :"+attendenceId, e);
		}
	}
	
//	http://localhost:8083/attendence/addattendence
	@PostMapping("/addattendence")
	public ResponseEntity<Attendence> addAttendence(@Valid @RequestBody Attendence attendence) {
		try {
			System.out.println(attendence);
			Attendence a= attendenceService.addAttendence(attendence);
		 
			return new ResponseEntity<>(a,HttpStatus.OK);
		}catch(AttendenceException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add AttendenceData", e);
		}
	}
//	http://localhost:8083/attendence/updateattendencebyid/5
	@PutMapping("/updateattendencebyid/{id}")
	public ResponseEntity<Attendence> updateAttendence(@Valid @PathVariable(value = "id") Integer attendenceId, @RequestBody Attendence attendence) {
		try{
			
			Attendence a = attendenceService.updateAttendenceById(attendenceId, attendence);
			return new ResponseEntity<>(a,HttpStatus.OK);
		}catch(AttendenceException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
//	http://localhost:8083/attendence/attendencelist
	@GetMapping("/attendencelist")
	public ResponseEntity<List<Attendence>> getFacultyList(){
		try{
			List<Attendence> attendenceList=attendenceService.getAttendenceList();
			return new ResponseEntity<>(attendenceList,HttpStatus.OK);	
		}catch(AttendenceException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get attendenceList", e);
		}
	}
}
