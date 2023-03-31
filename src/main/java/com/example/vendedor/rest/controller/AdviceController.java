package com.example.vendedor.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.vendedor.exception.LojaNaoEncontrada;
import com.example.vendedor.rest.controller.exception.ApiError;

@RestControllerAdvice
public class AdviceController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors().stream()
				.map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiError(messages);
	}
	
	@ExceptionHandler(LojaNaoEncontrada.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleLojaNaoEncontrada(LojaNaoEncontrada ex) {
		return new ApiError(ex.getMessage());
	}
}




