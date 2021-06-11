package egedeniz.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.CV;

public interface CvDao extends JpaRepository<CV, Integer>{

	CV getByJobSeeker_Id(int id);
}
