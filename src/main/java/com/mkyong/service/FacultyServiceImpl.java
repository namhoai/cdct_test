package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entites.Faculty;
import com.mkyong.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService{
	
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public boolean addFaculty(Faculty faculty) {
		return facultyRepository.save(faculty) != null;
	}

	@Override
	public void deleteFaculty(int id) {
		facultyRepository.delete(id);;
	}

	@Override
	public boolean editFaculty(Faculty faculty) {
		return facultyRepository.save(faculty) != null;
	}

	@Override
	public List<Faculty> getListFaculty() {
		// TODO Auto-generated method stub
		return facultyRepository.findAll();
	}

	@Override
	public Faculty getFaculty(int id) {
		// TODO Auto-generated method stub
		return facultyRepository.findOne(id);
	}

	
}
