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

import egedeniz.hrms.business.abstracts.JobAdvertisementService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.JobAdvertisement;
import egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<JobAdvertisement>>> getAll(){
		var result = this.jobAdvertisementService.getAll();
		if(result.isSuccess()) {
			return new ResponseEntity<DataResult<List<JobAdvertisement>>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<JobAdvertisement>>>(result, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody JobAdvertisement jobAdvertisement){
		return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisement));
	}
	
	@PostMapping("/remove")
	public ResponseEntity<?> remove(@RequestParam int id){
		return ResponseEntity.ok(this.jobAdvertisementService.remove(id));
	}
	
	@GetMapping("/getActiveJobPostings")
	public ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>> activeJobPostings(){
		var result = this.jobAdvertisementService.activeJobPostings();
		if(result.isSuccess()) {
			return new ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByEmployer")
	public ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>> getByEmployer(@RequestParam int employerId){
		var result = this.jobAdvertisementService.getByEmployer(employerId);
		if(result.isSuccess()) {
			return new ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<JobAdvertisementDetailDto>>>(result, HttpStatus.BAD_REQUEST);
	}
}