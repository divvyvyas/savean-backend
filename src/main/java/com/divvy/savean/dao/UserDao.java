package com.divvy.savean.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.divvy.savean.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	@Query(value = "select * from user where username = ?1 and password = ?2", nativeQuery = true)
	List<User> login(String username, String password);
	
	Optional<User> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "update user set password = ?1 where username = ?2", nativeQuery = true)
	int updatePassword(String password, String username);
	
	@Modifying
	@Transactional
	@Query(value = "update user set password = ?2 where username = ?1", nativeQuery = true)
	int resetPassword(String username, String password);

}
