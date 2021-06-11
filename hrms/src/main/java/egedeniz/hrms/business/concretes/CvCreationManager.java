package egedeniz.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egedeniz.hrms.business.abstracts.CvCreationService;
import egedeniz.hrms.business.abstracts.CvService;
import egedeniz.hrms.business.abstracts.EducationService;
import egedeniz.hrms.business.abstracts.ExperienceService;
import egedeniz.hrms.business.abstracts.LanguageService;
import egedeniz.hrms.business.abstracts.ProgrammingLanguageService;
import egedeniz.hrms.business.abstracts.SocialMediaAddressService;
import egedeniz.hrms.business.abstracts.UserImageService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.entities.concretes.CV;
import egedeniz.hrms.entities.dtos.CvDetailDto;

@Service
public class CvCreationManager implements CvCreationService {

	private CvService cvService;
	private EducationService educationService;
	private ExperienceService experienceService;
	private LanguageService languageService;
	private ProgrammingLanguageService programmingLanguageService;
	private SocialMediaAddressService socialMediaAddressService;
	private UserImageService userImageService;
	
	@Autowired
	public CvCreationManager(CvService cvService, EducationService educationService,
			ExperienceService experienceService, LanguageService languageService,
			ProgrammingLanguageService programmingLanguageService,
			SocialMediaAddressService socialMediaAddressService,
			UserImageService userImageService) {
		this.cvService = cvService;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.languageService = languageService;
		this.programmingLanguageService = programmingLanguageService;
		this.socialMediaAddressService = socialMediaAddressService;
		this.userImageService = userImageService;
	}

	@Override
	public Result createCv(CV cv) {
//		var added = this.cvService.add(cv);
//		
//		if (!cv.getEducations().isEmpty()) {
//			for (var education : cv.getEducations()) {
//				education.getCv().setId(added.getData().getId());
//				this.educationService.add(education);
//			}
//		}
//		
//		if (!cv.getExperiences().isEmpty()) {
//			for (var experience : cv.getExperiences()) {
//				experience.getCv().setId(added.getData().getId());
//				this.experienceService.add(experience);
//			}
//		}
//		
//		if (!cv.getLanguages().isEmpty()) {
//			for (var language : cv.getLanguages()) {
//				language.getCv().setId(added.getData().getId());
//				this.languageService.add(language);
//			}
//		}
//		
//		if (!cv.getProgrammingLanguages().isEmpty()) {
//			for (var programmingLanguage : cv.getProgrammingLanguages()) {
//				programmingLanguage.getCv().setId(added.getData().getId());
//				this.programmingLanguageService.add(programmingLanguage);
//			}
//		}
//		
//		if (!cv.getSocialMediaAddresses().isEmpty()) {
//			for (var socialMediaAddress : cv.getSocialMediaAddresses()) {
//				socialMediaAddress.getCv().setId(added.getData().getId());
//				this.socialMediaAddressService.add(socialMediaAddress);
//			}
//		}
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<CvDetailDto> getByUserId(int userId) {
		
		var cvDetails = new CvDetailDto();
		
		var cv = this.cvService.getByUserId(userId);
		if (!cv.isSuccess()) {
			return new ErrorDataResult<CvDetailDto>(cv.getMessage());
		}
		
		cvDetails.setId(cv.getData().getId());
		cvDetails.setCoverLetter(cv.getData().getCoverLetter());
		cvDetails.setJobSeeker(cv.getData().getJobSeeker());
		
		var userImages = this.userImageService.getByUserCoverPhoto(userId);
		if (userImages.isSuccess()) {
			cvDetails.setCoverPhoto(userImages.getData().getImagePath());
		}
		
		var educations = this.educationService.getByUserId(userId);
		if (educations.isSuccess()) {
			cvDetails.setEducations(educations.getData());
		}
		
		var experiences = this.experienceService.getByUserId(userId);
		if (experiences.isSuccess()) {
			cvDetails.setExperiences(experiences.getData());
		}
		
		var languages = this.languageService.getByUserId(userId);
		if (languages.isSuccess()) {
			cvDetails.setLanguages(languages.getData());
		}
		
		var programmingLanguages = this.programmingLanguageService.getByUserId(userId);
		if (programmingLanguages.isSuccess()) {
			cvDetails.setProgrammingLanguages(programmingLanguages.getData());
		}
		
		var socialMediaAddresses = this.socialMediaAddressService.getByUserId(userId);
		if (socialMediaAddresses.isSuccess()) {
			cvDetails.setSocialMediaAddresses(socialMediaAddresses.getData());
		}
		
		return new SuccessDataResult<CvDetailDto>(cvDetails);
	}

}
