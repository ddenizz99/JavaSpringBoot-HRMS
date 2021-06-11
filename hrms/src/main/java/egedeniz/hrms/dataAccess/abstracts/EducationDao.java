package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer> {

	List<Education> getByJobSeeker_Id(int userId);
	
	List<Education> getByJobSeeker_IdOrderByGraduationDateDesc(int userId);
}
