package com.practica.lista;



import com.practica.genericas.Coordenada;

/**
 * Nodo para la lista de coordenadas. En el guardamos cuántas personas  están
 * en una coordenada  en un momento temporal. 
 * También guardaremos la lista de personas que están en esa coordenada en un 
 * momento en concreto
 */
public class NodoPosicion {
	private Coordenada coordenada;	
	private int numPersonas;
	private NodoPosicion siguiente;
	
	
	public NodoPosicion() {
		super();
		siguiente = null;
	}

	
	
	
	public NodoPosicion(Coordenada coordenada,  int numPersonas, NodoPosicion siguiente) {
		super();
		this.coordenada = coordenada;		
		this.numPersonas = numPersonas;
		this.siguiente = siguiente;
	}




	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public NodoPosicion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoPosicion siguiente) {
		this.siguiente = siguiente;
	}
	
}
