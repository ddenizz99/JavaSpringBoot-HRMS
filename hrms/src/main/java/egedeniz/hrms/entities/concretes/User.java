package egedeniz.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String password;
	
	@Column(name="date_of_registration")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfRegistration;
	
	private boolean status;
	
	//Join
	
	@JsonIgnore
	@OneToMany(mappedBy ="user")
	private List<UserImage> userImages;
}
