package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.VerificationCodeService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.VerificationCode;

@RestController
@RequestMapping("/api/verificationcodes")
public class VerificationCodesController {

	private VerificationCodeService verificationCodeService;

	@Autowired
	public VerificationCodesController(VerificationCodeService verificationCodeService) {
		this.verificationCodeService = verificationCodeService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<VerificationCode>>> getAll(){
		var result = this.verificationCodeService.getAll();
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<VerificationCode>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<VerificationCode>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/verifyUser")
	public ResponseEntity<Result> verifyUser(@RequestParam String code){
		var result = this.verificationCodeService.verifyUser(code);
		if (result.isSuccess()) {
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<VerificationCode>> getByUserId(@RequestParam int userId){
		var result = this.verificationCodeService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<VerificationCode>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<VerificationCode>>(result, HttpStatus.BAD_REQUEST);
	}
	
}
