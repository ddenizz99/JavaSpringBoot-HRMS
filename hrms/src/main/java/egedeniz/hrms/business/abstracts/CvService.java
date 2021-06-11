package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.CV;

public interface CvService {

	Result add(CV cv);
	
	DataResult<List<CV>> getAll();
	
	DataResult<CV> getByUserId(int userId);
}
