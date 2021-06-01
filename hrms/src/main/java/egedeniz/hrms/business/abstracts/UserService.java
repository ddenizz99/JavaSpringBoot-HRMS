package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.User;

public interface UserService {

	DataResult<List<User>> getAll();
	
	DataResult<User> findByEmailEquals(String email);
}
