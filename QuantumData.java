package com.search;

import java.sql.Time;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuantumData {

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date fromDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date toDate;
	
	private Time fromTime;
	private Time toTime;
	
	private Float quantum;
}
