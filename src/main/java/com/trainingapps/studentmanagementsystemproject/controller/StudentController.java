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
import com.trainingapps.studentmanagementsystemproject.entity.Student;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentNotFoundException;
import com.trainingapps.studentmanagementsystemproject.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
//	http://localhost:8083/student/getbyid/10
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") int studentId) {
		try{
			Student student= studentService.getStudentById(studentId);
			return new ResponseEntity<>(student,HttpStatus.OK);
		}catch(StudentNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get StudentData", e);
		}
	}
//	http://localhost:8083/student/addstudent
	@PostMapping("/addstudent")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student,BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()) {
				//System.out.println(bindingResult.getAllErrors().toString());
				throw new StudentNotFoundException(bindingResult.getAllErrors().toString());
			}
			Student s= studentService.addStudent(student);
			return new ResponseEntity<>(s,HttpStatus.OK);
		}catch(StudentNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
//	http://localhost:8083/student/updatestudentbyid/5
	@PutMapping("/updatestudentbyid/{id}")
	public ResponseEntity<Student> updateStudent(@Valid @PathVariable(value = "id") Integer studentId, @RequestBody Student student,BindingResult bindingResult) {
		try{
			if(bindingResult.hasErrors()) {
				//System.out.println(bindingResult.getAllErrors().toString());
				throw new StudentNotFoundException(bindingResult.getAllErrors().toString());
			}
			Student s = studentService.updateStudentById(studentId, student);
			return new ResponseEntity<>(s,HttpStatus.OK);
		}catch(StudentNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
//	http://localhost:8083/student/delete/9
	@DeleteMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		try{
			studentService.deleteStudent(studentId);
			return studentId + " is deleted";
		}catch(StudentNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete course", e);
		}
	}
//	http://localhost:8083/student/studentlist
	@GetMapping("/studentlist")
	public ResponseEntity<List<Student>> getStudentList(){
		try{
			List<Student> studentList=studentService.getStudentList();
			return new ResponseEntity<>(studentList,HttpStatus.OK);	
		}catch(StudentNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get StudentList", e);
		}
	}
	
}
