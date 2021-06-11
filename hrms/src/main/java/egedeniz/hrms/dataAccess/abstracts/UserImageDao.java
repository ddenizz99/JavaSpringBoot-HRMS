package egedeniz.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egedeniz.hrms.entities.concretes.UserImage;

public interface UserImageDao extends JpaRepository<UserImage, Integer> {

	List<UserImage> getByUser_Id(int id);
	
	UserImage getByUser_IdAndCoverPhoto(int id, boolean coverPhoto);
}
