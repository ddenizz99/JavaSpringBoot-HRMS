package egedeniz.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Integer>{

	VerificationCode getByCode(String code);
	
	VerificationCode getByUserId(int userId);
}
