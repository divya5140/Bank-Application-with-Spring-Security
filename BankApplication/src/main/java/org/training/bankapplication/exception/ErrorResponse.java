package org.training.bankapplication.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	long errorcode;
	List<String> errorMessage;
	public ErrorResponse(long l, List<String> errorMessage) {
		super();
		this.errorcode = l;
		this.errorMessage = errorMessage;
	}
	

}
