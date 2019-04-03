package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mkyong.entites.Country;
import com.mkyong.service.CountryService;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/admin/addCountry")
    public String addCountry(@ModelAttribute("country") Country country) {
		countryService.addCountry(country);
        return "redirect:/admin/listCountry";
    }
	
	@GetMapping("/admin/listCountry")
    public String showListCountry(Model model) {
		List<Country> listCountry = countryService.getListCountries();
		Country country = new Country();
		model.addAttribute("listCountry", listCountry);
		model.addAttribute("country", country);
		return "listCountry";
    }
}
