package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.Language;

public interface LanguageDao extends JpaRepository<Language, Integer>{

	List<Language> getByJobSeeker_Id(int userId);
}
