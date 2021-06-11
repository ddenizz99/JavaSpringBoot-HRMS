package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageService {

	Result add(ProgrammingLanguage programmingLanguage);
	
	DataResult<List<ProgrammingLanguage>> getByUserId(int userId);
}
