package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkyong.entites.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	Country getCountryByName(String name);
}
