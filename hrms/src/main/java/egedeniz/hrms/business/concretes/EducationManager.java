package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.EducationService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.EducationDao;
import egedeniz.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService {

	private EducationDao educationDao;
	
	@Autowired
	public EducationManager(EducationDao educationDao) {
		this.educationDao = educationDao;
	}

	@Override
	public Result add(Education education) {
		this.educationDao.save(education);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<List<Education>> getByUserId(int userId) {
		var result = this.educationDao.getByJobSeeker_Id(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Education>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Education>>(result);
	}

	@Override
	public DataResult<List<Education>> getByUserIdOrderByGraduationDate(int userId) {
		var result = this.educationDao.getByJobSeeker_IdOrderByGraduationDateDesc(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Education>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Education>>(result);
	}

}
