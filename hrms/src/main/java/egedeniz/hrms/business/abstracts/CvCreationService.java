package egedeniz.hrms.business.abstracts;


import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.CV;
import egedeniz.hrms.entities.dtos.CvDetailDto;

public interface CvCreationService {

	Result createCv(CV cv);
	
	DataResult<CvDetailDto> getByUserId(int userId);
}
