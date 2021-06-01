package egedeniz.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.Employers;

public interface EmployersDao extends JpaRepository<Employers, Integer>{

}
