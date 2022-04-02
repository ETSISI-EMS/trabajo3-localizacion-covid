package com.practica.lista;

public class NodoPersonas {
	private String documento;
	private NodoPersonas siguiente;
	
	public NodoPersonas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NodoPersonas(String documento, NodoPersonas siguiente) {
		super();
		this.documento = documento;
		this.siguiente = siguiente;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public NodoPersonas getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoPersonas siguiente) {
		this.siguiente = siguiente;
	}
}
