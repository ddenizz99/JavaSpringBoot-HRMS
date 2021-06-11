package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.CvService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.CvDao;
import egedeniz.hrms.entities.concretes.CV;

@Service
public class CvManager implements CvService {

	private CvDao cvDao;
	
	@Autowired
	public CvManager(CvDao cvDao) {
		this.cvDao = cvDao;
	}

	@Override
	public DataResult<List<CV>> getAll() {
		var result = this.cvDao.findAll();
		return new SuccessDataResult<List<CV>>(result);
	}

	@Override
	public Result add(CV cv) {
		this.cvDao.save(cv);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<CV> getByUserId(int userId) {
		var result = this.cvDao.getByJobSeeker_Id(userId);
		if (result == null) {
			return new ErrorDataResult<CV>(Messages.emptyData);
		}
		return new SuccessDataResult<CV>(result);
	}

}
