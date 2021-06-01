package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelService {

	DataResult<List<SystemPersonnel>> getAll();
}
