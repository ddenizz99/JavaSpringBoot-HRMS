package egedeniz.hrms.entities.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerForRegisterDto {

	@NotBlank
	@NotNull
	private String companyName;
	
	@NotBlank
	@NotNull
	private String webAddress;
	
	@NotBlank
	@NotNull
	@Pattern(regexp="[0-9\\s]{12}")
	private String phoneNumber;
	
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
