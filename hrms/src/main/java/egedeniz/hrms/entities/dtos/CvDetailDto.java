package egedeniz.hrms.entities.dtos;

import java.util.List;

import egedeniz.hrms.entities.concretes.Education;
import egedeniz.hrms.entities.concretes.Experience;
import egedeniz.hrms.entities.concretes.JobSeeker;
import egedeniz.hrms.entities.concretes.Language;
import egedeniz.hrms.entities.concretes.ProgrammingLanguage;
import egedeniz.hrms.entities.concretes.SocialMediaAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CvDetailDto {

	private int id;
	
	private String coverPhoto;
	
	private String coverLetter;
	
	private JobSeeker jobSeeker;
	
	private List<Education> educations;
	
	private List<Experience> experiences;
	
	private List<Language> languages;
	
	private List<ProgrammingLanguage> programmingLanguages;
	
	private List<SocialMediaAddress> socialMediaAddresses;
}
