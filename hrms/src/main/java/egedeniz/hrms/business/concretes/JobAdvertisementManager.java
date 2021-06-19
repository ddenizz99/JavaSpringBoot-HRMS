package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.JobAdvertisementService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.JobAdvertisementDao;
import egedeniz.hrms.entities.concretes.JobAdvertisement;
import egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		var result = this.jobAdvertisementDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(result);
	}

	@Override
	public DataResult<List<JobAdvertisementDetailDto>> activeJobPostings() {
		var result = this.jobAdvertisementDao.activeJobPostings();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobAdvertisementDetailDto>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobAdvertisementDetailDto>>(result);
	}

	@Override
	public DataResult<List<JobAdvertisementDetailDto>> getByEmployer(int employerId) {
		var result = this.jobAdvertisementDao.getByEmployer(employerId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobAdvertisementDetailDto>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobAdvertisementDetailDto>>(result);
	}

	@Override
	public Result remove(int id) {
		var jobAdvertisement = this.jobAdvertisementDao.getById(id);
		jobAdvertisement.setStatus(false);
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public DataResult<List<JobAdvertisementDetailDto>> activeJobPostingsByReleaseDate() {
		var result = this.jobAdvertisementDao.activeJobPostingsByReleaseDate();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<JobAdvertisementDetailDto>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<JobAdvertisementDetailDto>>(result);
	}

	@Override
	public DataResult<JobAdvertisement> getById(int id) {
		var result = this.jobAdvertisementDao.findById(id);
		if (result != null) {
			return new SuccessDataResult<JobAdvertisement>(result);
		}
		return new ErrorDataResult<JobAdvertisement>(Messages.emptyData);
	}

}
