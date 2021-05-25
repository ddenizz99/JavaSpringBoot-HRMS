package egedeniz.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egedeniz.hrms.business.abstracts.UserService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<User>>> getAll(){
		return new ResponseEntity<DataResult<List<User>>>(this.userService.getAll(),HttpStatus.OK);
	}
	
}
