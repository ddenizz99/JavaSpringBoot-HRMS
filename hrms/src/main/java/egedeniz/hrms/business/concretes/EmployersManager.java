package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.EmployersService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.dataAccess.abstracts.EmployersDao;
import egedeniz.hrms.entities.concretes.Employers;

@Service
public class EmployersManager implements EmployersService{

	private EmployersDao employersDao;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao) {
		this.employersDao = employersDao;
	}

	@Override
	public DataResult<List<Employers>> getAll() {
		var result = this.employersDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<Employers>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<Employers>>(result);
	}

	@Override
	public DataResult<Employers> add(Employers employers) {
		var result = this.employersDao.save(employers);
		return new SuccessDataResult<Employers>(result, Messages.added);
	}

}
