package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entites.Candidate;
import com.mkyong.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService{
	
	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	public boolean addCandidate(Candidate candidate) {
		return candidateRepository.save(candidate) != null;
	}

	@Override
	public List<Candidate> getListCandidateByType(int typeCandidate) {
		return candidateRepository.findListCandidatesByTypeCandidate(typeCandidate);
	}
	

}
