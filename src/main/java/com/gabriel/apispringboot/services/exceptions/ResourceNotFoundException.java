package com.gabriel.apispringboot.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String entity,Object id)
	{
		super(entity + " with id " + id + " not found.");
	}

}
