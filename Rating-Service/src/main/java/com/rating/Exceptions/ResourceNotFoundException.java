package com.rating.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super("Resource not Found !!");
		// TODO Auto-generated constructor stub
	}
	
	

}
