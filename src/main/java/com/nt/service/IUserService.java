package com.nt.service;

import java.util.List;

import com.nt.entities.Credentials;
import com.nt.entities.UserInfo;
import com.nt.entities.Userdummy;

public interface IUserService {
	public UserInfo saveUser(UserInfo user);
	public List<Credentials>findByMail(String mail);
	public List<Object[]>findStatesByCountry(String countryName);
	public List<Object[]>findCitiesByState(String stateName);
	public boolean sendMail(String to, String body, String msg);
	public boolean existByMail(Credentials cr);
	public List<String>getAllContry();
	public List<String>getAllState();
	public List<String>getAllCity();
	public int updatePassByMail(String mail,String pass);
	public String findPasswordByMail(String mail);
	public UserInfo copyData(Userdummy dummmy);
	

}
