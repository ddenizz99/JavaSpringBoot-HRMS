package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Education;

public interface EducationService {

	Result add(Education education);
	
	DataResult<List<Education>> getByUserId(int userId);
	
	DataResult<List<Education>> getByUserIdOrderByGraduationDate(int userId);
}
