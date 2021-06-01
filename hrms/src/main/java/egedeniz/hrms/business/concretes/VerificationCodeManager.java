package egedeniz.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.VerificationCodeService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.ErrorResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.VerificationCodeDao;
import egedeniz.hrms.entities.concretes.VerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService{

	private VerificationCodeDao verificationCodeDao;
	
	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao) {
		this.verificationCodeDao = verificationCodeDao;
	}

	@Override
	public DataResult<List<VerificationCode>> getAll() {
		var result = this.verificationCodeDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<VerificationCode>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<VerificationCode>>(result);
	}

	@Override
	public Result add(VerificationCode verificationCode) {
		this.verificationCodeDao.save(verificationCode);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result verifyUser(String code) {
		var result = this.verificationCodeDao.getByCode(code);
		if (result != null) {
			result.setVerified(true);
			result.setDateOfVerified(LocalDate.now());
			this.verificationCodeDao.save(result);
			return new SuccessResult(Messages.userVerified);
		}
		return new ErrorResult(Messages.emptyData);
	}
	
	@Override
	public String generateCode() {
		String uuid = UUID.randomUUID().toString();
        return uuid;
	}

	@Override
	public DataResult<VerificationCode> getByUserId(int userId) {
		var result = this.verificationCodeDao.getByUserId(userId);
		if(result == null) {
			return new ErrorDataResult<VerificationCode>(Messages.emptyData);
		}
		return new SuccessDataResult<VerificationCode>(result);
	}

}
