package com.trainingapps.studentmanagementsystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.studentmanagementsystemproject.entity.StudentFee;
@Repository
public interface IStudentFeeRepository extends JpaRepository<StudentFee, Integer>{

}