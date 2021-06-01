package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.JobPosition;

public interface JobPositionService {

	Result add(JobPosition jobPosition);
	
	DataResult<List<JobPosition>> getAll();
	
	DataResult<JobPosition> getByTitle(String title);
}
