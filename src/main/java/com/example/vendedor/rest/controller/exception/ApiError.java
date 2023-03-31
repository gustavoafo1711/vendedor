package com.example.vendedor.rest.controller.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiError {
	private List<String> errors;

	public ApiError(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiError(String message) {
		this.errors = Arrays.asList(message);
	}
	

}
