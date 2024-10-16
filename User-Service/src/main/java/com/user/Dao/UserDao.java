package com.user.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.Model.User;

@Repository
public interface UserDao extends JpaRepository<User,String> {
	
	
 
	
}
