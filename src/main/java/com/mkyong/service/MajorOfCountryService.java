package com.mkyong.service;

import java.util.List;

import com.mkyong.entites.Country;
import com.mkyong.entites.MajorOfCountry;

public interface MajorOfCountryService {
	List<MajorOfCountry> getListMajorOfCountryByCountry(Country country);
	
	boolean addMajorOfCountryToCountry(MajorOfCountry majorOfCountry,Country country);
}
