package egedeniz.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.UserImage;

public interface UserImageService {

	Result add(int userId, boolean coverPhoto, MultipartFile multipartFile);
	
	DataResult<List<UserImage>> getByUserId(int userId);
	
	DataResult<UserImage> getByUserCoverPhoto(int userId);
}
