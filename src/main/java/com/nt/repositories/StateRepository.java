package com.nt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.entities.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	@Query(value = "SELECT s.s_name FROM Country c JOIN  State s WHERE c.cid=s.country_id And c.c_name=:name", nativeQuery=true )
	
	public List<Object[]> findStatesByCountry(String name);
	
	@Query("select sName from State")
	public List<String>getAllState();
}
