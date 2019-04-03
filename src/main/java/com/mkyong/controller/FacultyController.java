package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mkyong.entites.Faculty;
import com.mkyong.service.FacultyService;

@Controller
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@GetMapping("/admin/addFaculty")
    public String addFaculty(@ModelAttribute("faculty") Faculty faculty) {
    	facultyService.addFaculty(faculty);
        return "redirect:/admin/listFaculty";
    }
	
	@GetMapping("/admin/listFaculty")
    public String showListFaculty(Model model) {
		List<Faculty> listFaculty = facultyService.getListFaculty();
		Faculty faculty = new Faculty();
		model.addAttribute("listFaculty", listFaculty);
		model.addAttribute("faculty", faculty);
		return "listFaculty";
    }
}
