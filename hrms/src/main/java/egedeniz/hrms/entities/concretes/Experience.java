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
@Table(name="experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="job_position")
	private String jobPosition;
	
	@Column(name="start_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(name="end_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;
}
