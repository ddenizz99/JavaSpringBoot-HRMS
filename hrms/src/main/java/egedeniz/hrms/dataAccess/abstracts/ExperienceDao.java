package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import egedeniz.hrms.entities.concretes.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer> {

	List<Experience> getByJobSeeker_Id(int userId);
	
	List<Experience> getByJobSeeker_IdOrderByEndDateDesc(int userId);
}
