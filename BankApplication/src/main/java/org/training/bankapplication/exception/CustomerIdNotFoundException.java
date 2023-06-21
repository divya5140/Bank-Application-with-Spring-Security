package org.training.bankapplication.exception;

public class CustomerIdNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

public CustomerIdNotFoundException()
{
	super();
}
	
		public CustomerIdNotFoundException(String message)
		{
			super(message);
		}
	}


