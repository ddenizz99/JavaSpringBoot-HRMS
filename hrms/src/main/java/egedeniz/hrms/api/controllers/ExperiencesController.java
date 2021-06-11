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

import egedeniz.hrms.business.abstracts.ExperienceService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Experience;

@RestController
@RequestMapping("/api/experiences")
public class ExperiencesController {
	
	private ExperienceService experienceService;

	@Autowired
	public ExperiencesController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Experience experience) {
		return this.experienceService.add(experience);
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<Experience>>> getByUserId(@RequestParam int userId){
		var result = this.experienceService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<Experience>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<Experience>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserIdOrderByEndDate")
	public ResponseEntity<DataResult<List<Experience>>> getByUserIdOrderByEndDate(@RequestParam int userId){
		var result = this.experienceService.getByUserIdOrderByEndDate(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<Experience>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<Experience>>>(result,HttpStatus.BAD_REQUEST);
	}

}
