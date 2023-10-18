package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nt.entities.Country;
import com.nt.entities.Credentials;
import com.nt.entities.UserInfo;
import com.nt.entities.Userdummy;
import com.nt.repositories.CityRepository;
import com.nt.repositories.CountryRepository;
import com.nt.repositories.CredentialRepo;
import com.nt.repositories.StateRepository;
import com.nt.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
	 @Autowired
	    private JavaMailSender mailSender;
	@Autowired
private CountryRepository countryRepo;
	@Autowired
private StateRepository stateRepo;
	@Autowired
private CityRepository cityRepo;
	@Autowired
private UserRepo userRepo;
	@Autowired
private CredentialRepo cRepo;
	@Override
	public UserInfo saveUser(UserInfo user) {
		log.info("save mthod is executed from service");
		return userRepo.save(user);
	}

	@Override
	public List<Credentials> findByMail(String mail) {
		log.info("findByMail mthod is executed from service");
		// TODO Auto-generated method stub
		return cRepo.findPassByMaail(mail);
	}

	@Override
	public List<Object[]> findStatesByCountry(String countryName) {
		// TODO Auto-generated method stub
		log.info("findStatesByCountry mthod is executed from service");
		return stateRepo.findStatesByCountry(countryName);
	}

	@Override
	public List<Object[]> findCitiesByState(String stateName) {
		// TODO Auto-generated method stub
		log.info("findCitiesByState mthod is executed from service");
		return cityRepo.findCityByState(stateName);
	}

	@Override
	public boolean sendMail(String to, String body, String subject) {
		log.info("sendMail mthod is executed from service");
		try {
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);

	        mailSender.send(message);
	    
		return true;
		}
		catch(Exception e) {
			log.error("error eccurd while sending tha mail to Mail:"+to+", excepiton is"+e.getMessage());
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean existByMail(Credentials  cr) {
		log.info("existByMail mthod is executed from service");
		Example<Credentials>mp=Example.of(cr);
		
		return cRepo.exists(mp);
	}

	@Override
	public List<String> getAllContry() {
		// TODO Auto-generated method stub
		log.info("getAllContry() mthod is executed from service");
		return countryRepo.getAllContry();
	}

	@Override
	public List<String> getAllState() {
		// TODO Auto-generated method stub
		log.info("getAllState mthod is executed from service");
		return stateRepo.getAllState();
	}

	@Override
	public List<String> getAllCity() {
		// TODO Auto-generated method stub
		log.info("getAllCity mthod is executed from service");
		return cityRepo.getAllCity();
	}
	@Override
	public UserInfo copyData(Userdummy dummmy) {
		log.info("copyData mthod is executed from service");
		UserInfo info=new UserInfo(dummmy.getFirstName(),dummmy.getLastName(),dummmy.getPhNo(),dummmy.getDob(),dummmy.getGender(),dummmy.getCountry(),dummmy.getState(),dummmy.getCity());
	return info;
	}

	@Override
	public int updatePassByMail(String mail,String pass) {
		// TODO Auto-generated method stub
		log.info("updatePassMyMail() mthod is executed from service");
		return cRepo.updatePassword(mail, pass);
	}

	@Override
	public String findPasswordByMail(String mail) {
		log.info("findPassMyMail() mthod is executed from service");
		// TODO Auto-generated method stub
		return cRepo.findPassByMail(mail);
	}

}
