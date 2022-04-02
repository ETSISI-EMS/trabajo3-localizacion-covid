package com.practica.excecption;


public class EmsDuplicatePersonException extends Exception{
	public EmsDuplicatePersonException() {
		super("PERSONA DUPLICADA!");
		// TODO Auto-generated constructor stub
	}

	public EmsDuplicatePersonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
