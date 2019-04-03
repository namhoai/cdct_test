package com.mkyong.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.mkyong.entites.Candidate;
import com.mkyong.entites.Country;
import com.mkyong.entites.Faculty;
import com.mkyong.entites.MajorOfCountry;
import com.mkyong.service.CountryService;
import com.mkyong.service.FacultyService;
import com.mkyong.service.MajorOfCountryService;

@Controller
public class DefaultController {
	
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private MajorOfCountryService majorOfCountryService;
	
    @GetMapping("/")
    public String home(Model model) {
    	model.addAttribute("candidate", new Candidate());
        return "/index";
    }
    
    @GetMapping("/admin")
    public String index(Model model) {
    	Candidate candidate = new Candidate();
    	model.addAttribute("candidate",candidate);
        return "/home";
    }

    @GetMapping("/a")
    public String admin(Model model) {
    	Faculty faculty = new Faculty();
    	model.addAttribute("faculty",faculty);
        return "/abc";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @GetMapping("/register")
    public String index(Model model, @ModelAttribute("candidate") Candidate candidate, HttpServletRequest request, @RequestParam(value = "dob", required = false) String dob) {
    	if (dob != null) {
	    	try {
	    		String dateOfBirth = dob.replaceAll("\\s","");
				Date date1= new SimpleDateFormat("dd-mm-yyyy").parse(dateOfBirth);
				candidate.setDateOfBirth(date1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    	model.addAttribute("candidate", candidate);
    	String countryName = request.getParameter("countryName");
    	List<Faculty> listFaculty = facultyService.getListFaculty();
    	List<Country> listCountries = countryService.getListCountries();
    	List<MajorOfCountry> listMajorOfCountryByCountry = new ArrayList<>();
    	if(candidate != null && candidate.getCountryName() != null) {
    		Country country = countryService.getCountryByName(candidate.getCountryName());
    		listMajorOfCountryByCountry = majorOfCountryService.getListMajorOfCountryByCountry(country);
    	}
    	
    	model.addAttribute("faculties", listFaculty);
    	model.addAttribute("countries", listCountries);
    	model.addAttribute("majors", listMajorOfCountryByCountry);
        return "/register";
    }
    
    @GetMapping("/changeCountry")
    public String changeCountry(@RequestParam(value = "countryName", required = false) String countryName) throws JsonProcessingException {
    	Country country = countryService.getCountryByName(countryName);
    	List<MajorOfCountry> listMajorOfCountryByCountry = majorOfCountryService.getListMajorOfCountryByCountry(country);
    	ObjectMapper mapper = new ObjectMapper();
    	String data = mapper.writeValueAsString(listMajorOfCountryByCountry);
    	
    	return data;
    	
    }
}
