package egedeniz.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="job_seeker")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) 
@PrimaryKeyJoinColumn(name = "user_id",referencedColumnName = "id") 
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cvs","educations","experiences","languages","programmingLanguages","socialMediaAddresses"})
public class JobSeeker extends User{
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="identification_number")
	private String identificationNumber;
	
	@Column(name="date_of_birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	//Join
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<CV> cvs;
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<Education> educations;
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<Experience> experiences;
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<Language> languages;
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<ProgrammingLanguage> programmingLanguages;
	
	@OneToMany(mappedBy ="jobSeeker")
	private List<SocialMediaAddress> socialMediaAddresses;
}
