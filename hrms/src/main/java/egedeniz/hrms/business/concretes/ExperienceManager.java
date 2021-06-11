package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.ExperienceService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.ExperienceDao;
import egedeniz.hrms.entities.concretes.Experience;

@Service
public class ExperienceManager implements ExperienceService {

	private ExperienceDao experienceDao;
	
	@Autowired
	public ExperienceManager(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

	@Override
	public DataResult<List<Experience>> getByUserId(int userId) {
		var result = this.experienceDao.getByJobSeeker_Id(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Experience>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Experience>>(result);
	}
	
	@Override
	public DataResult<List<Experience>> getByUserIdOrderByEndDate(int userId) {
		var result = this.experienceDao.getByJobSeeker_IdOrderByEndDateDesc(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Experience>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Experience>>(result);
	}

	@Override
	public Result add(Experience experience) {
		this.experienceDao.save(experience);
		return new SuccessResult(Messages.added);
	}

}
