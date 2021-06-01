package egedeniz.hrms.business.abstracts;

import egedeniz.hrms.core.utilities.results.DataResult;
import egedeniz.hrms.core.utilities.results.Result;
import egedeniz.hrms.entities.concretes.Employers;
import egedeniz.hrms.entities.concretes.JobSeeker;
import egedeniz.hrms.entities.concretes.User;
import egedeniz.hrms.entities.dtos.EmployerForRegisterDto;
import egedeniz.hrms.entities.dtos.JobSeekerForRegisterDto;
import egedeniz.hrms.entities.dtos.UserForLoginDto;

public interface AuthService {

    DataResult<User> login(UserForLoginDto userForLoginDto);
    
    DataResult<JobSeeker> jobSeekerRegister(JobSeekerForRegisterDto jobSeekerForRegisterDto);
    
    DataResult<Employers> employerRegister(EmployerForRegisterDto employerForRegisterDto);
    
    Result userExists(String email);

}
