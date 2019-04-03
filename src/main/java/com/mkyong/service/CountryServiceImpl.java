package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entites.Country;
import com.mkyong.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public List<Country> getListCountries() {
		return countryRepository.findAll();
	}

	@Override
	public boolean addCountry(Country country) {
		return countryRepository.save(country) != null;
	}

	@Override
	public Country getCountryById(int id) {
		// TODO Auto-generated method stub
		return countryRepository.getOne(id);
	}

	@Override
	public Country getCountryByName(String name) {
		// TODO Auto-generated method stub
		return countryRepository.getCountryByName(name);
	}

}
