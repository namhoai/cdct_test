package com.mkyong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkyong.entites.Country;
import com.mkyong.entites.MajorOfCountry;

public interface MajorOfCountryRepository extends JpaRepository<MajorOfCountry, Integer>{
	List<MajorOfCountry> findListMajorOfCountriesByCountry(Country country);
	
	
}
