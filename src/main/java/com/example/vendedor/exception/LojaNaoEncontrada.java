package com.example.vendedor.exception;

public class LojaNaoEncontrada extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LojaNaoEncontrada() {
		super("Loja não encontrada!!!");
		
	}

}
