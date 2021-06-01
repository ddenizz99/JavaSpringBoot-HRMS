package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.JobPositionService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.business.BusinessRules;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.ErrorResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.JobPositionDao;
import egedeniz.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		var result = this.jobPositionDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobPosition>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobPosition>>(result);
	}

	@Override
	public Result add(JobPosition jobPosition) {
		Result result = BusinessRules.run(
				this.existsByTitle(jobPosition.getTitle())
				);
		
		if (result != null){
            return result;
        }
		
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<JobPosition> getByTitle(String title) {
		var result = this.jobPositionDao.getByTitle(title);
		if (result == null) {
			return new ErrorDataResult<JobPosition>(Messages.emptyData);
		}
		return new SuccessDataResult<JobPosition>(result);
	}
	
	//Business codes
	
	private Result existsByTitle(String title) {
		var result = this.getByTitle(title).isSuccess();
		if (!result) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.dataAlreadyExists);
	}

}
