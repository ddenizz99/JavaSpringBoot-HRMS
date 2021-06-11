package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageDao extends JpaRepository<ProgrammingLanguage, Integer> {

	List<ProgrammingLanguage> getByJobSeeker_Id(int userId);
}
