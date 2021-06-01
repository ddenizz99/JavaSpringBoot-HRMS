package egedeniz.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import egedeniz.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer>{

	@Query("From JobSeeker where email=:email or identificationNumber=:identificationNumber")
	JobSeekerDao getByEmailOrIdentificationNumber(String email, String identificationNumber);
}
