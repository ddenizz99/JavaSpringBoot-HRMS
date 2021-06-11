package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egedeniz.hrms.business.abstracts.UserImageService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.UserImage;

@RestController
@RequestMapping("/api/UserImages")
public class UserImagesController {

	private UserImageService userImageService;

	@Autowired
	public UserImagesController(UserImageService userImageService) {
		this.userImageService = userImageService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestParam(value="userId") int userId, @RequestParam(value="coverPhoto") boolean coverPhoto, @RequestParam(value="imageFile") MultipartFile imageFile){
		
		return ResponseEntity.ok(this.userImageService.add(userId, coverPhoto, imageFile));
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<UserImage>>> getByUserId(@RequestParam int userId){
		var result = this.userImageService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<UserImage>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<UserImage>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserCoverPhoto")
	public ResponseEntity<DataResult<UserImage>> getByUserCoverPhoto(@RequestParam int userId){
		var result = this.userImageService.getByUserCoverPhoto(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<UserImage>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<UserImage>>(result,HttpStatus.BAD_REQUEST);
	}
}
