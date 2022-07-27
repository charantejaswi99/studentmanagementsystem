package com.trainingapps.studentmanagementsystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trainingapps.studentmanagementsystemproject.entity.Faculty;
@Repository
public interface IFacultyRepository extends JpaRepository<Faculty, Integer> {

	Faculty getByLoginId(String loginId);

}