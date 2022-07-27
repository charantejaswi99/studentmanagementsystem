package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.trainingapps.studentmanagementsystemproject.entity.Attendence;
import com.trainingapps.studentmanagementsystemproject.exceptions.AttendenceException;
import com.trainingapps.studentmanagementsystemproject.repository.IAttendence;

@Service
@Transactional
public class AttendenceServiceImpl implements AttendenceService{
	
	@Autowired
	IAttendence attendenceRepository;

	@Override
	public Attendence getAttendenceById(Integer attendenceId) throws AttendenceException {
		try{
			Optional<Attendence> optional= attendenceRepository.findById(attendenceId);

			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new AttendenceException("Invalid attendenceId");
			}
		}catch(DataAccessException e) {
			throw new AttendenceException(e.getMessage(),e);
		}
	}

	@Override
	public List<Attendence> getAttendenceList() throws AttendenceException {
		try {
			List<Attendence> attendenceList= attendenceRepository.findAll();
			return attendenceList;
		}catch(DataAccessException e) {
			throw new AttendenceException(e.getMessage(),e);
		}
	}

	@Override
	public Attendence addAttendence(Attendence attendence) throws AttendenceException {
		try {
			attendence.setAttendencePercentage((double) (attendence.getAttendedClasses() * 100 / attendence.getTotalClasses()));
			attendence.setModifiedBy("Admin");
			return attendenceRepository.save(attendence);
		}catch(DataAccessException e) {
			throw new AttendenceException(e.getMessage(),e);
		}
		
	}

	@Override
	public Attendence updateAttendenceById(Integer attendenceId, Attendence attendence) throws AttendenceException {
         try {
        	 Attendence a =null;
 			Optional<Attendence> att = attendenceRepository.findById(attendenceId);
 			if(att.isPresent()) {
 				att.get().setAttendedClasses(attendence.getAttendedClasses());
 				att.get().setTotalClasses(attendence.getTotalClasses());
 				att.get().setAttendencePercentage((double) (attendence.getAttendedClasses() * 100 / attendence.getTotalClasses()));
 				att.get().setModifiedBy("Admin");
 				Attendence savedAttendence= attendenceRepository.save(att.get());
 				a = savedAttendence;
 			}else {
				throw new AttendenceException("Attendence not found by that Id");
			}
			return a;
         }catch(DataAccessException e) {
 			throw new AttendenceException(e.getMessage(),e);
 		}
	}

}
