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
@Table(name="job_advertisements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisement {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="job_description")
	private String jobDescription;
	
	@Column(name="salary_scale_min")
	private double salaryScaleMin;
	
	@Column(name="salary_scale_max")
	private double salaryScaleMax;
	
	@Column(name="number_of_open_positions")
	private int numberOfOpenPositions;
	
	@Column(name="application_deadline")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate applicationDeadline;
	
	@Column(name="release_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate releaseDate;
	
	private boolean status;
	
	//Join
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private Employers employer;
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name="job_position_id")
	private JobPosition jobPosition;
	
}
