package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Language;

public interface LanguageService {

	Result add(Language language);
	
	DataResult<List<Language>> getByUserId(int userId);
}
