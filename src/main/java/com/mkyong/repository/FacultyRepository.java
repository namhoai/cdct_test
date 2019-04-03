package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkyong.entites.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	Faculty getFacultyByName(String name);
}
