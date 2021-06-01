package egedeniz.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egedeniz.hrms.business.abstracts.AuthService;
import egedeniz.hrms.business.abstracts.EmployersService;
import egedeniz.hrms.business.abstracts.JobSeekerService;
import egedeniz.hrms.business.abstracts.SystemPersonnelService;
import egedeniz.hrms.business.abstracts.UserService;
import egedeniz.hrms.business.abstracts.VerificationCodeService;
import egedeniz.hrms.business.adapters.personService.PersonService;
import egedeniz.hrms.business.constants.Messages;
import egedeniz.hrms.core.utilities.business.BusinessRules;
import egedeniz.hrms.core.utilities.mail.MailService;
import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.ErrorDataResult;
import egedeniz.hrms.core.utilities.results.ErrorResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.core.utilities.results.SuccessDataResult;
import egedeniz.hrms.core.utilities.results.SuccessResult;
import egedeniz.hrms.entities.concretes.Employers;
import egedeniz.hrms.entities.concretes.JobSeeker;
import egedeniz.hrms.entities.concretes.User;
import egedeniz.hrms.entities.concretes.VerificationCode;
import egedeniz.hrms.entities.dtos.EmployerForRegisterDto;
import egedeniz.hrms.entities.dtos.JobSeekerForRegisterDto;
import egedeniz.hrms.entities.dtos.UserForLoginDto;

@Service
public class AuthManager implements AuthService{

	private UserService userService;
	private JobSeekerService jobSeekerService;
	private EmployersService employersService;
	private SystemPersonnelService systemPersonnelService;
	private PersonService personService;
	private MailService mailService;
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public AuthManager(UserService userService, JobSeekerService jobSeekerService, PersonService personService, MailService mailService, VerificationCodeService verificationCodeService, EmployersService employersService, SystemPersonnelService systemPersonnelService) {
		this.userService = userService;
		this.jobSeekerService = jobSeekerService;
		this.employersService = employersService;
		this.systemPersonnelService = systemPersonnelService;
		this.personService = personService;
		this.mailService = mailService;
		this.verificationCodeService = verificationCodeService;
	}

	@Override
	public DataResult<User> login(UserForLoginDto userForLoginDto) {
		var userToCheck = this.userService.findByEmailEquals(userForLoginDto.getEmail());
		if (!userToCheck.isSuccess()) {
			return new ErrorDataResult<User>(Messages.userNotFound);
		}
		
		if (!userToCheck.getData().isStatus()) {
			return new ErrorDataResult<User>(Messages.authorizationDenied);
		}
		
		var verificationCode = this.verificationCodeService.getByUserId(userToCheck.getData().getId());
		if (verificationCode.isSuccess()) {
			if (!verificationCode.getData().isVerified()) {
				return new ErrorDataResult<User>(Messages.yourEmailAddressIsNotVerified);
			}
		}
		
		if (!userToCheck.getData().getPassword().equals(userForLoginDto.getPassword())) {
			return new ErrorDataResult<User>(Messages.passwordError);
		}
		return new SuccessDataResult<User>(Messages.successfulLogin);
	}

	@Override
	public Result userExists(String email) {
		var result = this.userService.findByEmailEquals(email);
		if (result.isSuccess()) {
			return new SuccessResult();
		}
		return new ErrorResult(result.getMessage());
	}

	@Override
	@Transactional
	public DataResult<JobSeeker> jobSeekerRegister(JobSeekerForRegisterDto jobSeekerForRegisterDto) {

		Result result = BusinessRules.run(
				this.doesThePasswordMatch(jobSeekerForRegisterDto.getPassword(), jobSeekerForRegisterDto.getRepeatPassword()),
				this.userExists(jobSeekerForRegisterDto.getEmail(), jobSeekerForRegisterDto.getIdentificationNumber()),
				this.isVerify(jobSeekerForRegisterDto.getIdentificationNumber(), jobSeekerForRegisterDto.getFirstName(), jobSeekerForRegisterDto.getLastName(), jobSeekerForRegisterDto.getDateOfBirth().getYear())
				);
		
		if (result != null){
            return new DataResult<JobSeeker>(null, result.isSuccess(), result.getMessage());
        }
		
		var user = new JobSeeker();
		user.setEmail(jobSeekerForRegisterDto.getEmail());
		user.setPassword(jobSeekerForRegisterDto.getPassword());
		user.setFirstName(jobSeekerForRegisterDto.getFirstName());
		user.setLastName(jobSeekerForRegisterDto.getLastName());
		user.setDateOfRegistration(LocalDate.now());
		user.setStatus(true);
		user.setDateOfBirth(jobSeekerForRegisterDto.getDateOfBirth());
		user.setIdentificationNumber(jobSeekerForRegisterDto.getIdentificationNumber());
		var addedUser = this.jobSeekerService.add(user);
		
		var code = this.verificationCodeService.generateCode();
		
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setUserId(addedUser.getData().getId());
		verificationCode.setCode(code);
		verificationCode.setVerified(false);
		verificationCode.setDateOfVerified(null);
		this.verificationCodeService.add(verificationCode);
		
		this.mailService.sendSimpleMessage(jobSeekerForRegisterDto.getEmail(), "Yeni Kayıt Doğrulama", "<h1>Yeni Kayıt Doğrulama</h1><br><p>Hesabınızı doğrulamak için aşağıdaki adrese tıklayınız.</p><br><a href=\"http://localhost:8080/api/verificationcodes/verifyUser?code=" + code + "\">Hasebı Doğrula</a>");
		
		return new SuccessDataResult<JobSeeker>(Messages.userRegistered);
	}
	
	@Override
	public DataResult<Employers> employerRegister(EmployerForRegisterDto employerForRegisterDto) {
		
		Result result = BusinessRules.run(
				this.doesThePasswordMatch(employerForRegisterDto.getPassword(), employerForRegisterDto.getRepeatPassword()),
				this.isEmailandWebsiteDomainSame(employerForRegisterDto.getEmail(), employerForRegisterDto.getWebAddress()),
				this.userExistsMail(employerForRegisterDto.getEmail())
				);
		
		if (result != null){
            return new DataResult<Employers>(null, result.isSuccess(), result.getMessage());
        }
		
		var user = new Employers();
		user.setEmail(employerForRegisterDto.getEmail());
		user.setPassword(employerForRegisterDto.getPassword());
		user.setCompanyName(employerForRegisterDto.getCompanyName());
		user.setPhoneNumber(employerForRegisterDto.getPhoneNumber());
		user.setWebAddress(employerForRegisterDto.getWebAddress());
		user.setDateOfRegistration(LocalDate.now());
		user.setStatus(true);
		var addedUser = this.employersService.add(user);
		
		var code = this.verificationCodeService.generateCode();
		
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setUserId(addedUser.getData().getId());
		verificationCode.setCode(code);
		verificationCode.setVerified(false);
		verificationCode.setDateOfVerified(null);
		this.verificationCodeService.add(verificationCode);
		
		//this.mailService.sendSimpleMessage(employerForRegisterDto.getEmail(), "Yeni Kayıt Doğrulama", "<h1>Yeni Kayıt Doğrulama</h1><br><p>Hesabınızı doğrulamak için aşağıdaki adrese tıklayınız.</p><br><a href=\"http://localhost:8080/api/verificationcodes/verifyUser?code=" + code + "\">Hasebı Doğrula</a>");
		
		return new SuccessDataResult<Employers>(Messages.userRegistered);
	}
	
	//Business Codes
	
	private Result userExists(String email, String identificationNumber) {
		var result = this.jobSeekerService.getByEmailOrIdentificationNumber(email, identificationNumber).isSuccess();
		if (!result) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.userAlreadyExists);
	}
	
	private Result userExistsMail(String email) {
		var result = this.userExists(email).isSuccess();
		if (!result) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.userAlreadyExists);
	}
	
	private Result isVerify(String identificationNumber, String firstName, String lastName, int yearOfBirth) {
		var result = this.personService.isVerify(identificationNumber, firstName, lastName, yearOfBirth);
		if (result) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.userCouldNotBeAuthenticated);
	}
	
	private Result doesThePasswordMatch(String password, String repeatPassword) {
		if (password.equals(repeatPassword)) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.passwordDoesNotMatch);
	}
	
	private Result isEmailandWebsiteDomainSame(String email, String webAddress) {
        String[] emailSplit = email.split("@");
        if (!emailSplit[1].equals(webAddress))
            return new ErrorResult(Messages.yourEmailAddressIsNotVerified);
        return new SuccessResult();
    }

}
