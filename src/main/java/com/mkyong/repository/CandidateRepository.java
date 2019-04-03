package com.mkyong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkyong.entites.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	List<Candidate> findListCandidatesByTypeCandidate(int typeCandidate);
}
