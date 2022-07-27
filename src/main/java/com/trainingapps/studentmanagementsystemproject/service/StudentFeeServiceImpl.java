package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.trainingapps.studentmanagementsystemproject.entity.StudentFee;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentFeeException;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentNotFoundException;
import com.trainingapps.studentmanagementsystemproject.repository.IStudentFeeRepository;

@Service
@Transactional
public class StudentFeeServiceImpl implements StudentFeeService{

	@Autowired
	IStudentFeeRepository studentFeeRepository;
	@Override
	public StudentFee getStudentFeeById(Integer studentFeeId) throws StudentFeeException {
		try{
			Optional<StudentFee> optional= studentFeeRepository.findById(studentFeeId);

			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new StudentNotFoundException("Invalid studentFeeId");
			}
		}catch(DataAccessException e) {
			throw new StudentFeeException(e.getMessage(),e);
		}
	}

	@Override
	public List<StudentFee> getStudentFeeList() throws StudentFeeException {
		try {
			List<StudentFee> studentFeeList= studentFeeRepository.findAll();
			return studentFeeList;
		}catch(DataAccessException e) {
			throw new StudentFeeException(e.getMessage(),e);
		}
	}

	@Override
	public StudentFee addStudentFee(StudentFee studentFee) throws StudentFeeException {
		try {
			operation(studentFee);
			return studentFeeRepository.save(studentFee);
		}catch(DataAccessException e) {
			throw new StudentFeeException(e.getMessage(),e);

		}
	}

	public void operation(StudentFee fee) {
		if(fee != null) {
			Double totFee = fee.getTutionFee() + fee.getBusFee() + fee.getOtherFee();
			fee.setTotalFee(totFee);
			if(fee.getTotalFee() >= fee.getPaidFee()) {
				fee.setDueFee(fee.getTotalFee() - fee.getPaidFee());
			}else {
				throw new StudentNotFoundException("TotalFee lessthan PaidFee");
			}
			fee.setModifiedBy("Admin");

		}	
	}

	@Override
	public StudentFee updateStudentFeeById(Integer studentFeeId, StudentFee StudentFee) throws StudentFeeException {
		try {
			StudentFee s =null;
			Optional<StudentFee> stu = studentFeeRepository.findById(studentFeeId);
			if(stu.isPresent()) {
				stu.get().setBusFee(StudentFee.getBusFee());
				stu.get().setOtherFee(StudentFee.getOtherFee());
				stu.get().setTutionFee(StudentFee.getTutionFee());
				operation(stu.get());
				StudentFee savedStudentFee = studentFeeRepository.save(stu.get());
				s = savedStudentFee;
				
			}else {
				throw new StudentFeeException("StudentFee not found by Id :"+studentFeeId);
			}
			return s;
		}catch(DataAccessException e) {
			throw new StudentFeeException(e.getMessage(),e);
		}

	}

}
