package com.nt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entities.UserInfo;

public interface UserRepo extends JpaRepository<UserInfo, Integer> {

}
