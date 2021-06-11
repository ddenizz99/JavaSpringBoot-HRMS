package egedeniz.hrms.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="social_media_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaAddress {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String platform;
	
	private String address;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;
}
