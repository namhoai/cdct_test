package com.mkyong.service;

import java.util.List;

import com.mkyong.entites.Candidate;

public interface CandidateService {
	boolean addCandidate(Candidate candidate);
	List<Candidate> getListCandidateByType(int typeCandidate);
}
