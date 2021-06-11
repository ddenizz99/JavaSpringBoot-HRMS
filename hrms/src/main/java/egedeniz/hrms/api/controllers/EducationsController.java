package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.EducationService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Education;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {

	private EducationService educationService;

	@Autowired
	public EducationsController(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<Education>>> getByUserId(@RequestParam int userId){
		var result = this.educationService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<Education>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<Education>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserIdOrderByGraduationDate")
	public ResponseEntity<DataResult<List<Education>>> getByUserIdOrderByGraduationDate(@RequestParam int userId){
		var result = this.educationService.getByUserIdOrderByGraduationDate(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<Education>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<Education>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Education education) {
		return this.educationService.add(education);
	}
}
