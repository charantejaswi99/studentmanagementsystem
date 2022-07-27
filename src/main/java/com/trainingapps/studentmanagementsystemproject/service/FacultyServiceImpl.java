package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.trainingapps.studentmanagementsystemproject.entity.Faculty;
import com.trainingapps.studentmanagementsystemproject.exceptions.FacultyException;
import com.trainingapps.studentmanagementsystemproject.repository.IFacultyRepository;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	IFacultyRepository facultyRepository;
	String authe ="";
	@Override
	public Faculty getFacultyById(Integer facultyId) throws FacultyException {
		try{
			Optional<Faculty> optional= facultyRepository.findById(facultyId);

			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new FacultyException("Faculty Not Found");
			}
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);
		}
	}


	@Override
	public String login(String loginId, String password) throws FacultyException {
		try {
			authe = "Login Failed";
			System.out.println(loginId);
			System.out.println(password);
			Faculty f = facultyRepository.getByLoginId(loginId);		
			if(f != null) {
				if(f.getPassword().equals(password)) {
					authe = "Login Successful Welcome "+f.getFirstName()+" "+f.getLastName();
				}else {
					authe = "Wrong Password";
				}
			}else {
				authe = "No Id Found With "+loginId;
			}
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);
		}
		return authe;

	}

	@Override
	public List<Faculty> getFacultyList() throws FacultyException {
		try {
			List<Faculty> facultyList= facultyRepository.findAll();
			return facultyList;
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);
		}
	}

	@Override
	public Faculty addFaculty(Faculty faculty) throws FacultyException {
		try {
			System.out.println(getFacultyList() != null);
			if(getFacultyList() != null) {
				boolean res = getFacultyList().stream().anyMatch(f -> f.getLoginId().equals(faculty.getLoginId()));
				System.out.println(res);			
				if(res) {
					throw new FacultyException("Login Id Already Exsists");
				}
			}

			facultyRepository.save(faculty);
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);

		}

		return faculty;
	}

	@Override
	public Faculty updateFacultyById(Integer facultyId, Faculty faculty) throws FacultyException {
		try {
			Faculty f =null;
			Optional<Faculty> fac = facultyRepository.findById(facultyId);
			if(fac.isPresent()) {
				fac.get().setFirstName(faculty.getFirstName());
				fac.get().setLastName(faculty.getLastName());			
				fac.get().setMobileNumber(faculty.getMobileNumber());
				System.out.println(fac.get());
				if(getFacultyList() != null) {
					List<String> loginIds = getFacultyList().stream().map(Faculty::getLoginId).collect(Collectors.toList());
					System.out.println(loginIds);
					if(loginIds.contains(faculty.getLoginId())) {
						throw new FacultyException("Login Id Already Exsists");
					}else {
						fac.get().setLoginId(faculty.getLoginId());
					}
				}
				Faculty savedFaculty = facultyRepository.save(fac.get());
				f = savedFaculty;						
			}else {
				throw new FacultyException("Faculty Not Found");
			}
			return f;
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);

		}


	}

	public void checkLoginId(Faculty faculty) {
		System.out.println(faculty);
		String id = faculty.getLoginId();
		boolean res = getFacultyList().stream().anyMatch(f -> f.getLoginId().equals(faculty.getLoginId()));
		System.out.println(res);			
		if(res) {
			throw new FacultyException("Login Id Already Exsists");
		}else {
			faculty.setLoginId(id);
		}
	}

	@Override
	public void deleteFaculty(Integer facultyId) throws FacultyException {
		try {
			facultyRepository.deleteById(facultyId);
		}catch(DataAccessException e) {
			throw new FacultyException(e.getMessage(),e);

		}
	}

}
