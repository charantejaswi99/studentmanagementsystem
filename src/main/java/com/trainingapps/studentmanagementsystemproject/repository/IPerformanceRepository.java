package com.trainingapps.studentmanagementsystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.studentmanagementsystemproject.entity.Perfomance;
@Repository
public interface IPerformanceRepository extends JpaRepository<Perfomance, Integer>{

}