package com.practica.excecption;


public class EmsPersonNotFoundException extends Exception{

	public EmsPersonNotFoundException() {
		super("PERSONA NO ENCONTRADA!");
	}

	public EmsPersonNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
