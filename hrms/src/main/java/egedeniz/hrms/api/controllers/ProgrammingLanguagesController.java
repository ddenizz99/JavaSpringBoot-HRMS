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

import egedeniz.hrms.business.abstracts.ProgrammingLanguageService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.ProgrammingLanguage;

@RestController
@RequestMapping("/api/programmingLanguages")
public class ProgrammingLanguagesController {

	private ProgrammingLanguageService programmingLanguageService;

	@Autowired
	public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
		this.programmingLanguageService = programmingLanguageService;
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<ProgrammingLanguage>>> getByUserId(@RequestParam int userId){
		var result = this.programmingLanguageService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<ProgrammingLanguage>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<ProgrammingLanguage>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody ProgrammingLanguage programmingLanguage) {
		return this.programmingLanguageService.add(programmingLanguage);
	}
}
