package egedeniz.hrms.business.adapters.personService;

import org.springframework.stereotype.Service;

@Service
public class PersonManager implements PersonService{

	@Override
	public boolean isVerify(String identificationNumber, String firstName, String lastName, int dateOfBirthYear) {
		return true;
	}

}
