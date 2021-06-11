package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Experience;

public interface ExperienceService {

	Result add(Experience experience);
	
	DataResult<List<Experience>> getByUserId(int userId);
	
	DataResult<List<Experience>> getByUserIdOrderByEndDate(int userId);
}
