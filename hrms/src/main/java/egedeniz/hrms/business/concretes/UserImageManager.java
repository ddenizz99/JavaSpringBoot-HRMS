package egedeniz.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egedeniz.hrms.business.abstracts.UserImageService;
import egedeniz.hrms.business.abstracts.UserService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.helpers.UploadService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.ErrorResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.dataAccess.abstracts.UserImageDao;
import egedeniz.hrms.entities.concretes.UserImage;

@Service
public class UserImageManager implements UserImageService{

	private UserImageDao userImageDao;
	private UploadService uploadService;
	private UserService userService;
	
	@Autowired
	public UserImageManager(UserImageDao userImageDao, UploadService uploadService, UserService userService) {
		this.userImageDao = userImageDao;
		this.uploadService = uploadService;
		this.userService = userService;
	}

	@Override
	public Result add(int userId, boolean coverPhoto, MultipartFile multipartFile) {
		var uploadImage = this.uploadService.uploadImage(multipartFile);
		var user = this.userService.getById(userId);
		
		if (!user.isSuccess()) {
			return new ErrorResult(user.getMessage());
		}
		
		var userImage = new UserImage();
		userImage.setCoverPhoto(coverPhoto);
		userImage.setUser(user.getData());
		userImage.setDate(LocalDate.now());
		userImage.setStatus(true);
		userImage.setImagePath(uploadImage.getData());
		
		this.userImageDao.save(userImage);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<List<UserImage>> getByUserId(int userId) {
		var result = this.userImageDao.getByUser_Id(userId);
		if (result.isEmpty()) {
			return new ErrorDataResult<List<UserImage>>(Messages.emptyData);
		}
		return new SuccessDataResult<List<UserImage>>(result);
	}

	@Override
	public DataResult<UserImage> getByUserCoverPhoto(int userId) {
		var result = this.userImageDao.getByUser_IdAndCoverPhoto(userId, true);
		if (result == null) {
			return new ErrorDataResult<UserImage>(Messages.emptyData);
		}
		return new SuccessDataResult<UserImage>(result);
	}

}
