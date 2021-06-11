package egedeniz.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.SocialMediaAddressService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.SocialMediaAddressDao;
import egedeniz.hrms.entities.concretes.SocialMediaAddress;

@Service
public class SocialMediaAddressManager implements SocialMediaAddressService{

	private SocialMediaAddressDao socialMediaAddressDao;
	
	@Autowired
	public SocialMediaAddressManager(SocialMediaAddressDao socialMediaAddressDao) {
		this.socialMediaAddressDao = socialMediaAddressDao;
	}
	
	@Override
	public Result add(SocialMediaAddress socialMediaAddress) {
		this.socialMediaAddressDao.save(socialMediaAddress);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<List<SocialMediaAddress>> getByUserId(int userId) {
		var result = this.socialMediaAddressDao.getByJobSeeker_Id(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<SocialMediaAddress>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<SocialMediaAddress>>(result);
	}

}
