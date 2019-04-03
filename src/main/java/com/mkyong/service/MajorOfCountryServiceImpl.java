package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entites.Country;
import com.mkyong.entites.MajorOfCountry;
import com.mkyong.repository.MajorOfCountryRepository;

@Service
public class MajorOfCountryServiceImpl implements MajorOfCountryService{
	
	@Autowired
	private MajorOfCountryRepository majorOfCountryRepository;
	@Override
	public List<MajorOfCountry> getListMajorOfCountryByCountry(Country country) {
		// TODO Auto-generated method stub
		return majorOfCountryRepository.findListMajorOfCountriesByCountry(country);
	}
	@Override
	public boolean addMajorOfCountryToCountry(MajorOfCountry majorOfCountry, Country country) {
		majorOfCountry.setCountry(country);
		return majorOfCountryRepository.save(majorOfCountry) != null;
	}

}
