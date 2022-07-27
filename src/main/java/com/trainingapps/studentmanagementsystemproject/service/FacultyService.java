package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import com.trainingapps.studentmanagementsystemproject.entity.Faculty;
import com.trainingapps.studentmanagementsystemproject.exceptions.FacultyException;


public interface FacultyService {

	public abstract Faculty getFacultyById(Integer facultyId) throws FacultyException;
	public abstract String login(String loginId,String password) throws FacultyException;
    public abstract List<Faculty> getFacultyList() throws FacultyException;
    public abstract Faculty addFaculty(Faculty faculty) throws FacultyException;
    public abstract Faculty updateFacultyById(Integer facultyId,Faculty faculty) throws FacultyException;
    public void deleteFaculty(Integer facultyId) throws FacultyException;
}
