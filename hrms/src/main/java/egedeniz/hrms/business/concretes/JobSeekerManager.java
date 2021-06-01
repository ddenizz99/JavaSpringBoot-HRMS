package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.JobSeekerService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.dataAccess.abstracts.JobSeekerDao;
import egedeniz.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService{

	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao) {
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		var result = this.jobSeekerDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobSeeker>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobSeeker>>(result);
	}

	@Override
	public DataResult<JobSeeker> add(JobSeeker jobSeeker) {
		var result = this.jobSeekerDao.save(jobSeeker);
		return new SuccessDataResult<JobSeeker>(result, Messages.added);
	}

	@Override
	public DataResult<JobSeeker> getByEmailOrIdentificationNumber(String email, String identificationNumber) {
		var result = this.jobSeekerDao.getByEmailOrIdentificationNumber(email, identificationNumber);
		if (result == null) {
			return new ErrorDataResult<JobSeeker>(Messages.emptyData);
		}
		return new SuccessDataResult<JobSeeker>();
	}
	
}
