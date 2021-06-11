package egedeniz.hrms.core.utilities.helpers;


import org.springframework.web.multipart.MultipartFile;

import egedeniz.hrms.core.utilities.results.DataResult;

public interface UploadService {

	DataResult<String> uploadImage(MultipartFile multipartFile);
}
