package egedeniz.hrms.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cvs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CV {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cover_letter")
	private String coverLetter;
	
	//Join
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;

}
