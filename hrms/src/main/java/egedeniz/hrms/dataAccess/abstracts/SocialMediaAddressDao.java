package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.SocialMediaAddress;

public interface SocialMediaAddressDao extends JpaRepository<SocialMediaAddress, Integer> {

	List<SocialMediaAddress> getByJobSeeker_Id(int userId);
}
