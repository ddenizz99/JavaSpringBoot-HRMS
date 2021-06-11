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

import egedeniz.hrms.business.abstracts.CvCreationService;
import egedeniz.hrms.business.abstracts.CvService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.CV;
import egedeniz.hrms.entities.dtos.CvDetailDto;

@RestController
@RequestMapping("/api/cvs")
public class CvsController {

	private CvService cvService;
	private CvCreationService cvCreationService;

	@Autowired
	public CvsController(CvService cvService, CvCreationService cvCreationService) {
		this.cvService = cvService;
		this.cvCreationService = cvCreationService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Result> add(@RequestBody CV cv){
		var result = this.cvService.add(cv);
		return new ResponseEntity<Result>(result,HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<CV>>> getAll(){
		var result = this.cvService.getAll();
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<CV>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<CV>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<CV>> getByUserId(@RequestParam int userId){
		var result = this.cvService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<CV>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<CV>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserIdDetail")
	public ResponseEntity<DataResult<CvDetailDto>> getByUserIdDetail(@RequestParam int userId){
		var result = this.cvCreationService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<CvDetailDto>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<CvDetailDto>>(result,HttpStatus.BAD_REQUEST);
	}
	
}
