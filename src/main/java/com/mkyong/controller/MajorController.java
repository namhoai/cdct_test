package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkyong.entites.Country;
import com.mkyong.entites.MajorOfCountry;
import com.mkyong.service.CountryService;
import com.mkyong.service.MajorOfCountryService;

@Controller
public class MajorController {

	@Autowired
	private MajorOfCountryService majorOfCountryService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/admin/addMajor")
    public String addMajor(@ModelAttribute("major") MajorOfCountry majorOfCountry) {
		Country country = countryService.getCountryById(majorOfCountry.getCountryId());
		majorOfCountryService.addMajorOfCountryToCountry(majorOfCountry, country);
        return "redirect:/admin/listMajor?idCountry="+majorOfCountry.getCountryId();
    }
	
	@GetMapping("/admin/listMajor")
    public String showListMajor(Model model, @Param("idCountry") int idCountry) {
		Country country = countryService.getCountryById(idCountry);
		List<MajorOfCountry> listMajor = majorOfCountryService.getListMajorOfCountryByCountry(country);
		MajorOfCountry major = new MajorOfCountry();
		major.setCountryId(country.getId());
		model.addAttribute("listMajor", listMajor);
		model.addAttribute("country", country);
		model.addAttribute("major", major);
		return "listMajor";
    }
}
