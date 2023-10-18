package com.nt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.entities.Credentials;

import jakarta.transaction.Transactional;

public interface CredentialRepo extends JpaRepository<Credentials, Integer> {
	@Query(" from Credentials Where email=:mail")
	public List<Credentials> findPassByMaail(String mail);
	@Query(" UPDATE Credentials SET password =:password WHERE email=:mail")
	@Modifying
	@Transactional
	public int updatePassword(String mail,String password);
	@Query("Select password from Credentials Where email=:mail")
	public String findPassByMail(String mail);

}
