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

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="start_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(name="graduation_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate graduationDate;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;
}
