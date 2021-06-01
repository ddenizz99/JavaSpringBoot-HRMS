package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.UserService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.dataAccess.abstracts.UserDao;
import egedeniz.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		var result = this.userDao.findAll();
		if (result.isEmpty()) {
			return new ErrorDataResult<List<User>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<User>>(result);
	}

	@Override
	public DataResult<User> findByEmailEquals(String email) {
		var result = this.userDao.findByEmailEquals(email);
		if (result != null) {
			return new SuccessDataResult<User>(result);
		}
		return new ErrorDataResult<User>(Messages.userNotFound);
	}

}
