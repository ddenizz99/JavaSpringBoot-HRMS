package egedeniz.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmailEquals(String email);
	
	User getById(int id);
}
