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

import egedeniz.hrms.business.abstracts.SocialMediaAddressService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.SocialMediaAddress;

@RestController
@RequestMapping("/api/socialMediaAddresses")
public class SocialMediaAddressesController {
	
	private SocialMediaAddressService socialMediaAddressService;

	@Autowired
	public SocialMediaAddressesController(SocialMediaAddressService socialMediaAddressService) {
		this.socialMediaAddressService = socialMediaAddressService;
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<SocialMediaAddress>>> getByUserId(@RequestParam int userId){
		var result = this.socialMediaAddressService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<SocialMediaAddress>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<SocialMediaAddress>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody SocialMediaAddress socialMediaAddress) {
		return this.socialMediaAddressService.add(socialMediaAddress);
	}

}
