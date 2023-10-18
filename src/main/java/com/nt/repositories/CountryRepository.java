package com.nt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entities.Country;
import com.nt.entities.State;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	@Query(value="SELECT c.c_name FROM Country c JOIN State s WHERE c.cid=s.country_id AND s.s_name=:state",nativeQuery=true)
	public Object[]findCountyByState(String state);
	@Query("select cName from Country")
	public List<String>getAllContry();
	

}
