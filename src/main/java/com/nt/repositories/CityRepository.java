package com.nt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	@Query(value="SELECT c.d_name FROM State s JOIN City c WHERE c.state_name=s.sid AND s.s_name=:state",nativeQuery=true )
	
	public List<Object[]>findCityByState(String state);
//"SELECT s.s_name FROM Country c JOIN  State s WHERE c.cid=s.country_id And c.c_name=:name"
	@Query("select dName from City")
	public List<String>getAllCity();
}
