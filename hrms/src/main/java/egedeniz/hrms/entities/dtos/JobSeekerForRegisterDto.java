package egedeniz.hrms.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerForRegisterDto {

	@NotBlank
	@NotNull
	private String firstName;
	
	@NotBlank
	@NotNull
	private String lastName;
	
	@NotBlank
	@NotNull
	private String identificationNumber;
	
	//@NotBlank
	//@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull
	@Min(value=6, message="Şifreniz minimum 6 karakterden oluşmalıdır.")
	private String password;
	
	@NotBlank
	@NotNull
	private String repeatPassword;
}
