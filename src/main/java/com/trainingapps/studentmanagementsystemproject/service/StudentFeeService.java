package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import com.trainingapps.studentmanagementsystemproject.entity.StudentFee;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentFeeException;

public interface StudentFeeService {
	public abstract StudentFee getStudentFeeById(Integer studentFeeId) throws StudentFeeException ;
    public abstract List<StudentFee> getStudentFeeList() throws StudentFeeException;
    public abstract StudentFee addStudentFee(StudentFee StudentFee) throws StudentFeeException;
    public abstract StudentFee updateStudentFeeById(Integer studentFeeId,StudentFee studentFee) throws StudentFeeException;
    
}
