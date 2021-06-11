package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import egedeniz.hrms.entities.concretes.JobAdvertisement;
import egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	@Query("Select new egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto(j.id,e.companyName,jp.title,j.numberOfOpenPositions,j.releaseDate,j.applicationDeadline) From Employers e Inner Join e.jobAdvertisements j Inner Join j.jobPosition jp where j.status = true")
	List<JobAdvertisementDetailDto> activeJobPostings();
	
	@Query("Select new egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto(j.id,e.companyName,jp.title,j.numberOfOpenPositions,j.releaseDate,j.applicationDeadline) From Employers e Inner Join e.jobAdvertisements j Inner Join j.jobPosition jp where j.status = true ORDER BY j.releaseDate DESC")
	List<JobAdvertisementDetailDto> activeJobPostingsByReleaseDate();
	
	@Query("Select new egedeniz.hrms.entities.dtos.JobAdvertisementDetailDto(j.id,e.companyName,jp.title,j.numberOfOpenPositions,j.releaseDate,j.applicationDeadline) From Employers e Inner Join e.jobAdvertisements j Inner Join j.jobPosition jp where e.id=:employerId and j.status = true")
	List<JobAdvertisementDetailDto> getByEmployer(int employerId);
}
