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
@Table(name="languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String language;
	
	private int level;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;
}
