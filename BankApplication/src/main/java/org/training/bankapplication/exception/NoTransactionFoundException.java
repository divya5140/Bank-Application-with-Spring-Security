package org.training.bankapplication.exception;

public class NoTransactionFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

public NoTransactionFoundException()
{
	super();
}
	
		public NoTransactionFoundException(String message)
		{
			super(message);
		}
	}