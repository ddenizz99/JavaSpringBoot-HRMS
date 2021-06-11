package egedeniz.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
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
@Table(name="user_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="image_path")
	private String imagePath;
	
	@Column(name="cover_photo")
	private boolean coverPhoto;
	
	private LocalDate date;
	
	private boolean status;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
}
