package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;

import com.trainingapps.studentmanagementsystemproject.entity.Student;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentNotFoundException;

public interface StudentService {
	public abstract Student getStudentById(Integer studentId) throws StudentNotFoundException ;
    public abstract List<Student> getStudentList() throws StudentNotFoundException;
    public abstract Student addStudent(Student student) throws StudentNotFoundException;
    public abstract Student updateStudentById(Integer studentId,Student student) throws StudentNotFoundException;
    public void deleteStudent(Integer studentId) throws StudentNotFoundException;
}
