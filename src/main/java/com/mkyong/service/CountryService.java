package com.mkyong.service;

import java.util.List;

import com.mkyong.entites.Country;

public interface CountryService {
	List<Country> getListCountries();
	boolean addCountry(Country country);
	
	Country getCountryById(int id);
	
	Country getCountryByName(String name);
}
