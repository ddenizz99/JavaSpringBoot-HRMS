package egedeniz.hrms.core.utilities.helpers;


import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import egedeniz.hrms.core.utilities.results.DataResult;

@Service
public class CloudinaryUploadManager implements UploadService{
	
	private Cloudinary cloudinary;
	
	public CloudinaryUploadManager() {
		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dpatnimru",
				"api_key", "789389579917234",
				"api_secret", "I41u0SlPRzYuMuOjF3E86YJYaLg"));
	}

	@Override
	public DataResult<String> uploadImage(MultipartFile multipartFile) {
		try {
			Map<String,String> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
			return new DataResult<String>(uploadResult.get("url"), true);
		} catch (Exception e) {
			return new DataResult<String>(null, false, e.getMessage());
		}
		
	}

}
