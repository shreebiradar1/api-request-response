package org.dnyanyog.repo;

import org.dnyanyog.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<Login, Long>{
	
	public Login findByUserNameAndPassword(String username, String password);
}
