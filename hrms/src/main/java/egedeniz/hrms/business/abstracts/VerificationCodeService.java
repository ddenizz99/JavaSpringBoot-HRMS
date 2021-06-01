package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService {

	Result add(VerificationCode verificationCode);
	
	Result verifyUser(String code);
	
	DataResult<List<VerificationCode>> getAll();
	
	DataResult<VerificationCode> getByUserId(int userId);
	
	String generateCode();
	
}
