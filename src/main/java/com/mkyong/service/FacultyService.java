package com.mkyong.service;

import java.util.List;

import com.mkyong.entites.Faculty;

public interface FacultyService {
	boolean addFaculty(Faculty faculty);
	void deleteFaculty(int id);
	boolean editFaculty(Faculty faculty);
	
	List<Faculty> getListFaculty();
	
	Faculty getFaculty(int id);
}
