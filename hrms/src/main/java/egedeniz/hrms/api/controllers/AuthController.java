package egedeniz.hrms.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.AuthService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.Employers;
import egedeniz.hrms.entities.concretes.JobSeeker;
import egedeniz.hrms.entities.concretes.User;
import egedeniz.hrms.entities.dtos.EmployerForRegisterDto;
import egedeniz.hrms.entities.dtos.JobSeekerForRegisterDto;
import egedeniz.hrms.entities.dtos.UserForLoginDto;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	private AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<DataResult<User>> login(@RequestBody UserForLoginDto userForLoginDto){
		var result = this.authService.login(userForLoginDto);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<User>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<User>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/registerjobSeeker")
	public ResponseEntity<DataResult<JobSeeker>> registerjobSeeker(@Valid @RequestBody JobSeekerForRegisterDto jobSeekerForRegisterDto){
		var result = this.authService.jobSeekerRegister(jobSeekerForRegisterDto);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<JobSeeker>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<JobSeeker>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/registerEmployer")
	public ResponseEntity<DataResult<Employers>> registerEmployer(@Valid @RequestBody EmployerForRegisterDto employerrForRegisterDto){
		var result = this.authService.employerRegister(employerrForRegisterDto);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<Employers>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<Employers>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException
	(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors 
		= new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		return errors;
	}
}
