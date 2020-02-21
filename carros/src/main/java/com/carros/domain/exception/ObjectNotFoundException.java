package com.carros.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5898687512515984380L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
