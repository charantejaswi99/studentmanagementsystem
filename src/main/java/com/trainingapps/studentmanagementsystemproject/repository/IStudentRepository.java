package com.trainingapps.studentmanagementsystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.studentmanagementsystemproject.entity.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer>{

}