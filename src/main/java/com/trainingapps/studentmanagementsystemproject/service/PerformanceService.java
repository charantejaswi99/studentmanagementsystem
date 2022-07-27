package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;

import com.trainingapps.studentmanagementsystemproject.entity.Perfomance;
import com.trainingapps.studentmanagementsystemproject.exceptions.PerformaneException;

public interface PerformanceService {

	public abstract Perfomance getPerformanceById(Integer performanceId) throws PerformaneException ;
	public abstract List<Perfomance> getPerfomanceList() throws PerformaneException;
	public abstract Perfomance addPerfomance(Perfomance perfomance) throws PerformaneException;
	public abstract Perfomance updatePerfomanceById(Integer perfomanceId,Perfomance perfomance) throws PerformaneException;
	
}
