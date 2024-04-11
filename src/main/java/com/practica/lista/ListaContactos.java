package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal ant = null, aux = lista;
		boolean encontrado = false;

		while (aux != null && !encontrado) {
			int comparacion = aux.getFecha().compareTo(p.getFechaPosicion());
			if (comparacion == 0) {
				encontrado = true;
				insertarActualizarCoordenada(aux, p);
			} else if (comparacion < 0) {
				ant = aux;
				aux = aux.getSiguiente();
			} else {
				break;
			}
		}

		if (!encontrado) {
			insertarNuevoNodoTemporal(p, ant, aux);
		}
	}

	private void insertarActualizarCoordenada(NodoTemporal nodoTemporal, PosicionPersona p) {
		NodoPosicion npActual = nodoTemporal.getListaCoordenadas();
		NodoPosicion npAnt = null;
		boolean npEncontrado = false;

		while (npActual != null && !npEncontrado) {
			if (npActual.getCoordenada().equals(p.getCoordenada())) {
				npEncontrado = true;
				npActual.setNumPersonas(npActual.getNumPersonas() + 1);
			} else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}

		if (!npEncontrado) {
			NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
			if (npAnt == null) {
				nodoTemporal.setListaCoordenadas(npNuevo);
			} else {
				npAnt.setSiguiente(npNuevo);
			}
		}
	}

	private void insertarNuevoNodoTemporal(PosicionPersona p, NodoTemporal ant, NodoTemporal siguiente) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(p.getFechaPosicion());
		nuevo.setSiguiente(siguiente);

		insertarActualizarCoordenada(nuevo, p);

		if (ant == null) {
			lista = nuevo;
		} else {
			ant.setSiguiente(nuevo);
		}
		this.size++;
	}


	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}
            }
            aux = aux.getSiguiente();
        }
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}
            }
            aux = aux.getSiguiente();
        }
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder cadena= new StringBuilder();
		NodoTemporal aux = lista;
		for(int cont=1; cont<size; cont++) {
			cadena.append(aux.getFecha().getFecha().toString());
			cadena.append(";").append(aux.getFecha().getHora().toString()).append(" ");
			aux=aux.getSiguiente();
		}
		cadena.append(aux.getFecha().getFecha().toString());
		cadena.append(";").append(aux.getFecha().getHora().toString());
		return cadena.toString();
	}
	
	
	
}
