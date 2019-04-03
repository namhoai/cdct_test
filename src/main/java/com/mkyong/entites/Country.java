package com.mkyong.entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	private List<MajorOfCountry> majorOfCountries;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MajorOfCountry> getMajorOfCountries() {
		return majorOfCountries;
	}

	public void setMajorOfCountries(List<MajorOfCountry> majorOfCountries) {
		this.majorOfCountries = majorOfCountries;
	}
	
	
}
