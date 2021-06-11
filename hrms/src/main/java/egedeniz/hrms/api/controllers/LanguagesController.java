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

import egedeniz.hrms.business.abstracts.LanguageService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Language;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<DataResult<List<Language>>> getByUserId(@RequestParam int userId){
		var result = this.languageService.getByUserId(userId);
		if (result.isSuccess()) {
			return new ResponseEntity<DataResult<List<Language>>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<DataResult<List<Language>>>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Language language) {
		return this.languageService.add(language);
	}
}
