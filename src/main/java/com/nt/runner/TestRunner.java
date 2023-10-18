package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.nt.repositories.CityRepository;
import com.nt.repositories.CountryRepository;
import com.nt.repositories.CredentialRepo;
import com.nt.repositories.StateRepository;
import com.nt.repositories.UserRepo;
//@Component
public class TestRunner implements CommandLineRunner {
	@Autowired
	private CountryRepository countyRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private UserRepo uRepo;
	@Autowired
	private CredentialRepo cRepo;
		@Override
		public void run(String... args) throws Exception {
			//uRepo.deleteAll();

//			List<Credentials> cr = cRepo.findPassByMaail("raghu@gmail.com");
//			cr.forEach(a->{
//				
//		if(	a.getUser().getState().equalsIgnoreCase("unLocked"))
//			System.out.println("your acount is active");
//		else System.out.println("your account is deactive");
//			});//
		//System.out.println(cRepo.findPassByMail("wardhan778@gmail.com"));
			//uRepo.deleteAll();
}
}
