package com.practica.excecption;


public class EmsInvalidTypeException extends Exception{

	public EmsInvalidTypeException() {
		super("TIPO DE ENTRADA NO VALIDA!");
		// TODO Auto-generated constructor stub
	}
	
	public EmsInvalidTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
