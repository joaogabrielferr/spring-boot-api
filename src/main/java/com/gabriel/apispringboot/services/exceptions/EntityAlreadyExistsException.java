package com.gabriel.apispringboot.services.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public EntityAlreadyExistsException(String entity,String field,String fieldValue)
	{
		super(entity + "with " + field + " " + fieldValue + " already exists");
	}
	
}
