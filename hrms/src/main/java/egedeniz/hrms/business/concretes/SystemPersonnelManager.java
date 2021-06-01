package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.SystemPersonnelService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.dataAccess.abstracts.SystemPersonnelDao;
import egedeniz.hrms.entities.concretes.SystemPersonnel;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {

	private SystemPersonnelDao systemPersonnelDao;
	
	@Autowired
	public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao) {
		this.systemPersonnelDao = systemPersonnelDao;
	}

	@Override
	public DataResult<List<SystemPersonnel>> getAll() {
		var result = this.systemPersonnelDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<SystemPersonnel>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<SystemPersonnel>>(result);
	}

}
