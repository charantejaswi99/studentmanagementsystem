package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import com.trainingapps.studentmanagementsystemproject.entity.Attendence;
import com.trainingapps.studentmanagementsystemproject.exceptions.AttendenceException;

public interface AttendenceService {

	public abstract Attendence getAttendenceById(Integer attendenceId) throws AttendenceException ;
	public abstract List<Attendence> getAttendenceList() throws AttendenceException;
	public abstract Attendence addAttendence(Attendence attendence) throws AttendenceException;
	public abstract Attendence updateAttendenceById(Integer attendenceId,Attendence attendence) throws AttendenceException;
}
