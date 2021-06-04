package egedeniz.hrms.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementDetailDto {

	private int id;
	
	private String companyName;
	
	private String jopPositionTitle;
	
	private int numberOfOpenPositions;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate releaseDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate applicationDeadline;
}
