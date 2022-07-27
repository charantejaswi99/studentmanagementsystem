package com.trainingapps.studentmanagementsystemproject.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.trainingapps.studentmanagementsystemproject.entity.Perfomance;
import com.trainingapps.studentmanagementsystemproject.exceptions.PerformaneException;
import com.trainingapps.studentmanagementsystemproject.repository.IPerformanceRepository;

@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService {
	
	@Autowired
	IPerformanceRepository perfomanceRepository;
	Perfomance p = null;

	@Override
	public Perfomance getPerformanceById(Integer performanceId) throws PerformaneException {
		try{
			Optional<Perfomance> optional= perfomanceRepository.findById(performanceId);

			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PerformaneException("Perfomance Not Found");
			}
		}catch(DataAccessException e) {
			throw new PerformaneException(e.getMessage(),e);
		}
	}

	@Override
	public List<Perfomance> getPerfomanceList() throws PerformaneException {
		try {
			List<Perfomance> perfomanceList= perfomanceRepository.findAll();
			return perfomanceList;
		}catch(DataAccessException e) {
			throw new PerformaneException(e.getMessage(),e);
		}
	}

	@Override
	public Perfomance addPerfomance(Perfomance perfomance) throws PerformaneException {
		try {
			p.setDuration(perfomance.getDuration());
			p.setTotalMarks(perfomance.getTotalMarks());
			p.setSubjectMap(perfomance.getSubjectMap());
			operation(perfomance);
			perfomanceRepository.save(p);
			
	}catch (DataAccessException e) {
		throw new PerformaneException(e.getMessage(),e);
	}
		return p;
	}

	public void operation(Perfomance perfomance) {
		
		Integer obtMarks = perfomance.getSubjectMap().values().stream().reduce(0, Integer::sum);
		p.setMarksObtained(obtMarks);
		p.setTotalPercentage((double) obtMarks * 100 /perfomance.getTotalMarks());
		p.setModifiedBy("Admin");
		
		Map<String,Integer> filteredMap = perfomance.getSubjectMap().entrySet()
				.stream().filter(x->x.getValue() <=35)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println(filteredMap);
		if(filteredMap.isEmpty()) {
			p.setResult("Pass");
		}else {
			p.setResult("Fail");
		}
	}
	
	@Override
	public Perfomance updatePerfomanceById(Integer perfomanceId, Perfomance perfomance) throws PerformaneException {
		Perfomance p =null;
		Optional<Perfomance> per = perfomanceRepository.findById(perfomanceId);
		if(per.isPresent()) {
			per.get().setDuration(perfomance.getDuration());
			per.get().setSubjectMap(perfomance.getSubjectMap());
			operation(per.get());
			Perfomance savedPerfomance = perfomanceRepository.save(per.get());
			p = savedPerfomance;
		}else {
			throw new PerformaneException ("Perfomance not found by given Id :"+perfomanceId);
		}
		return p;
	}
}
