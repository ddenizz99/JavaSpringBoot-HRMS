package egedeniz.hrms.business.adapters.personService;

public interface PersonService {

	boolean isVerify(String identificationNumber, String firstName, String lastName, int dateOfBirthYear);
}
