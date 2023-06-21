package org.training.bankapplication.exception;

public class AccountNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

public AccountNotFoundException()
{
	super();
}
	
		public AccountNotFoundException(String message)
		{
			super(message);
		}
	}
	
	

