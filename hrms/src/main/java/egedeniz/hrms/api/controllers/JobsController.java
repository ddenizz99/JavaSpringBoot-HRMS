package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.JobPositionService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

	private JobPositionService jobPositionService;

	@Autowired
	public JobsController(JobPositionService jobPositionService) {
		this.jobPositionService = jobPositionService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<JobPosition>>> getAll(){
		return new ResponseEntity<DataResult<List<JobPosition>>>(this.jobPositionService.getAll(),HttpStatus.OK);
	}
}
