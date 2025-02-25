package org.dnyanyog.repo;

import org.dnyanyog.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	@Modifying
	@Transactional
	public int deleteByFirstName(String name);
	
	public User findByFirstName(String name);
	
}
