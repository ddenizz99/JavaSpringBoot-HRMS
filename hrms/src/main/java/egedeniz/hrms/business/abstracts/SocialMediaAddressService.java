package egedeniz.hrms.business.abstracts;

import java.util.List;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.SocialMediaAddress;

public interface SocialMediaAddressService {

	Result add(SocialMediaAddress socialMediaAddress);
	
	DataResult<List<SocialMediaAddress>> getByUserId(int userId);
}
