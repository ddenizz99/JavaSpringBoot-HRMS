package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {

	DataResult<JobSeeker> add(JobSeeker jobSeeker);
	
	DataResult<List<JobSeeker>> getAll();
	
	DataResult<JobSeeker> getByEmailOrIdentificationNumber(String email, String identificationNumber);
}
