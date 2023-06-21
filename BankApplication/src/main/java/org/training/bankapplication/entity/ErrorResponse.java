package org.training.bankapplication.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorResponse {
	
	
	String errorcode;
	String errorMessage;
	public ErrorResponse(String string, String string2) {
		super();
		this.errorcode = string;
		this.errorMessage = string2;
	}
	

}
