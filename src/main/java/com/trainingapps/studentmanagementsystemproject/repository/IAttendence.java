package com.trainingapps.studentmanagementsystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.studentmanagementsystemproject.entity.Attendence;
@Repository
public interface IAttendence extends JpaRepository<Attendence, Integer>{

}
