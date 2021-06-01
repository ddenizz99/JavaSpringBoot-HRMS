package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.EmployersService;
import egedeniz.hrms.business.abstracts.JobSeekerService;
import egedeniz.hrms.business.abstracts.SystemPersonnelService;
import egedeniz.hrms.business.abstracts.UserService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.Employers;
import egedeniz.hrms.entities.concretes.JobSeeker;
import egedeniz.hrms.entities.concretes.SystemPersonnel;
import egedeniz.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;
	private JobSeekerService jobSeekerService;
	private EmployersService employersService;
	private SystemPersonnelService systemPersonnelService;

	@Autowired
	public UsersController(UserService userService, JobSeekerService jobSeekerService, EmployersService employersService, SystemPersonnelService systemPersonnelService) {
		this.userService = userService;
		this.jobSeekerService = jobSeekerService;
		this.employersService = employersService;
		this.systemPersonnelService = systemPersonnelService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<User>>> getAll(){
		return new ResponseEntity<DataResult<List<User>>>(this.userService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getalljobseeker")
	public ResponseEntity<DataResult<List<JobSeeker>>> getAllJobSeeker(){
		return new ResponseEntity<DataResult<List<JobSeeker>>>(this.jobSeekerService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getallemployers")
	public ResponseEntity<DataResult<List<Employers>>> getAllEmployers(){
		return new ResponseEntity<DataResult<List<Employers>>>(this.employersService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getallsystempersonnel")
	public ResponseEntity<DataResult<List<SystemPersonnel>>> getAllSystemPersonnel(){
		return new ResponseEntity<DataResult<List<SystemPersonnel>>>(this.systemPersonnelService.getAll(),HttpStatus.OK);
	}
	
}
