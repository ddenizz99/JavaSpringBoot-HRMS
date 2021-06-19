package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.JobAdvertisement;
import egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto;

public interface JobAdvertisementService {

	Result add(JobAdvertisement jobAdvertisement);
	
	Result remove(int id);
	
	DataResult<JobAdvertisement> getById(int id);
	
	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<List<JobAdvertisementDetailDto>> activeJobPostings();
	
	DataResult<List<JobAdvertisementDetailDto>> activeJobPostingsByReleaseDate();
	
	DataResult<List<JobAdvertisementDetailDto>> getByEmployer(int employerId);
	
}
