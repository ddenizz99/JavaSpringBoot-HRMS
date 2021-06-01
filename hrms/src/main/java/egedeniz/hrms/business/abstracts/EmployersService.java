package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Employers;

public interface EmployersService {

	DataResult<List<Employers>> getAll();
	
	DataResult<Employers> add(Employers employers);
}
