package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.LanguageService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.LanguageDao;
import egedeniz.hrms.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	@Override
	public Result add(Language language) {
		this.languageDao.save(language);
		return new SuccessResult(Messages.added);
		
	}

	@Override
	public DataResult<List<Language>> getByUserId(int userId) {
		var result = this.languageDao.getByJobSeeker_Id(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Language>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Language>>(result);
	}

}
