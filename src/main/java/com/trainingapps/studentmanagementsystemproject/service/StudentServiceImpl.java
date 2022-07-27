package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trainingapps.studentmanagementsystemproject.entity.Attendence;
import com.trainingapps.studentmanagementsystemproject.entity.Perfomance;
import com.trainingapps.studentmanagementsystemproject.entity.Student;
import com.trainingapps.studentmanagementsystemproject.entity.StudentFee;
import com.trainingapps.studentmanagementsystemproject.exceptions.StudentNotFoundException;
import com.trainingapps.studentmanagementsystemproject.repository.IAttendence;
import com.trainingapps.studentmanagementsystemproject.repository.IPerformanceRepository;
import com.trainingapps.studentmanagementsystemproject.repository.IStudentFeeRepository;
import com.trainingapps.studentmanagementsystemproject.repository.IStudentRepository;

@Transactional
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	IStudentRepository studentRepository;
	@Autowired
	IAttendence attendenceRepository;
	@Autowired
	IStudentFeeRepository studentFeeRepository;
	@Autowired
	IPerformanceRepository performanceRepository;

	Perfomance perfomance = null;
	StudentFee fee = null;
	Attendence attendence = null;

	PerformanceService performanceService;
	@Override
	public Student getStudentById(Integer studentId) throws StudentNotFoundException {
		try{
			Optional<Student> optional= studentRepository.findById(studentId);

			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new StudentNotFoundException("Invalid student");
			}
		}catch(DataAccessException e) {
			throw new StudentNotFoundException(e.getMessage(),e);
		}
	}

	@Override
	public List<Student> getStudentList() throws StudentNotFoundException {
		try {
			List<Student> studentList= studentRepository.findAll();
			return studentList;
		}catch(DataAccessException e) {
			throw new StudentNotFoundException(e.getMessage(),e);
		}
	}

	@Override
	public Student addStudent(Student student) throws StudentNotFoundException {
		try{
			operation(student);
			Student savedStudent= studentRepository.save(student);
			return savedStudent;
		}catch(DataAccessException e) {
			throw new StudentNotFoundException(e.getMessage(),e);
		}
	}

	@Override
	public Student updateStudentById(Integer studentId, Student student) throws StudentNotFoundException {
		try {
			Student s =null;
			Optional<Student> stu = studentRepository.findById(studentId);
			if(stu.isPresent()) {
				stu.get().setFirstName(student.getFirstName());
				stu.get().setLastName(student.getLastName());
				stu.get().setAddress(student.getAddress());
				stu.get().setStandard(student.getStandard());
				stu.get().setPhoneNumber(student.getPhoneNumber());
				stu.get().setSetion(student.getSetion());
				stu.get().setModifiedBy("Admin");
				operation(student);
				Student savedStudent= studentRepository.save(stu.get());
				s= savedStudent;
			}else {
				throw new StudentNotFoundException("Student not found by that Id");
			}
			return s;
		}catch(DataAccessException e) {
			throw new StudentNotFoundException(e.getMessage(),e);
		}
	}

	public void operation(Student student) {
		perfomance = student.getPerfomance();
		fee = student.getStudentFee();
		attendence = student.getAttendence();
		//performance logic
		if(perfomance!=null) {
			System.out.println(perfomance);
			Integer obtMarks = perfomance.getSubjectMap().values().stream().reduce(0, Integer::sum);
			perfomance.setMarksObtained(obtMarks);
			perfomance.setTotalPercentage((double) obtMarks * 100 /perfomance.getTotalMarks());
			perfomance.setModifiedBy("Admin");

			Map<String,Integer> filteredMap = perfomance.getSubjectMap().entrySet()
					.stream().filter(x->x.getValue() <=35)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			System.out.println(filteredMap);
			if(filteredMap.isEmpty()) {
				perfomance.setResult("Pass");
			}else {
				perfomance.setResult("Fail");
			}
			student.setPerfomance(perfomance);
			System.out.println(perfomance);
		}
		//performance logic

		//Studentfee Logic
		if(fee != null) {
			Double totFee = fee.getTutionFee() + fee.getBusFee() + fee.getOtherFee();
			fee.setTotalFee(totFee);
			if(fee.getTotalFee() >= fee.getPaidFee()) {
				fee.setDueFee(fee.getTotalFee() - fee.getPaidFee());
			}else {
				throw new StudentNotFoundException("TotalFee lessthan PaidFee");
			}
			fee.setModifiedBy("Admin");
			student.setStudentFee(fee);
		}		
		//Studentfee Logic

		//attendence logic
		if(attendence !=null) {
			attendence.setAttendencePercentage((double) (attendence.getAttendedClasses() * 100 / attendence.getTotalClasses()));
			attendence.setModifiedBy("Admin");
			student.setAttendence(attendence);
		}
		//attendence logic
	}
	
	@Override
	public void deleteStudent(Integer studentId) throws StudentNotFoundException {
		try{
			Optional<Student> stu = studentRepository.findById(studentId);
			if(stu.isPresent()) {
				attendenceRepository.deleteById(stu.get().getAttendence().getAttendenceId());
				studentFeeRepository.deleteById(stu.get().getStudentFee().getStudentFeeId());
				performanceRepository.deleteById(stu.get().getPerfomance().getPerfomanceId());
				
			}
			studentRepository.deleteById(studentId);
		}catch(DataAccessException e) {
			throw new StudentNotFoundException(e.getMessage(),e);
		}

	}
	
}



