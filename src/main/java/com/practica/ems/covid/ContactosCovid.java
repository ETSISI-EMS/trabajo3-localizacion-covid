package com.practica.ems.covid;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.Constantes;
import com.practica.genericas.Coordenada;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Persona;
import com.practica.genericas.PosicionPersona;
import com.practica.lista.ListaContactos;

public class ContactosCovid {
	private Poblacion poblacion;
	private Localizacion localizacion;
	private ListaContactos listaContactos;
	private static final Logger LOGGER = Logger.getLogger(ContactosCovid.class.getName());


	public ContactosCovid() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}



	public ListaContactos getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListaContactos listaContactos) {
		this.listaContactos = listaContactos;
	}

	
	public void loadData(String data, boolean reset) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException,
			EmsDuplicatePersonException, EmsDuplicateLocationException {
		// borro información anterior
		resetData(reset);
		String datas[] = dividirEntrada(data);
		for (String linea : datas) {
			String datos[] = this.dividirLineaData(linea);
			if (!datos[0].equals("PERSONA") && !datos[0].equals("LOCALIZACION")) {
				throw new EmsInvalidTypeException();
			}
			if (datos[0].equals("PERSONA")) {
				processPersonData(datos);
			}
			if (datos[0].equals("LOCALIZACION")) {
				processLocalizacionData(datos);
			}
		}
	}

	private void resetData(boolean reset) {
		if (reset) {
			this.poblacion = new Poblacion();
			this.localizacion = new Localizacion();
			this.listaContactos = new ListaContactos();
		}
	}

	private void processPersonData(String[] datos) throws EmsDuplicatePersonException, EmsInvalidNumberOfDataException {
		if (datos.length != Constantes.MAX_DATOS_PERSONA) {
			throw new EmsInvalidNumberOfDataException("El número de datos para PERSONA es menor de 8");
		}
		this.poblacion.addPersona(this.crearPersona(datos));
	}

	private void processLocalizacionData (String[] datos) throws EmsInvalidNumberOfDataException, EmsDuplicateLocationException {
		if (datos.length != Constantes.MAX_DATOS_LOCALIZACION) {
			throw new EmsInvalidNumberOfDataException("El número de datos para LOCALIZACION es menor de 6");
		}
		PosicionPersona pp = this.crearPosicionPersona(datos);
		this.localizacion.addLocalizacion(pp);
		this.listaContactos.insertarNodoTemporal(pp);
	}

	public void loadDataFile(String fichero, boolean reset) {
		File archivo = new File(fichero);
		try (FileReader fr = new FileReader(archivo);
			 BufferedReader br = new BufferedReader(fr)) {

			if (reset) {
				resetData();
			}
			processFileData(br);

		} catch (IOException | EmsInvalidTypeException | EmsInvalidNumberOfDataException |
				 EmsDuplicateLocationException | EmsDuplicatePersonException e) {
			LOGGER.log(Level.SEVERE, "Error processing file: " + fichero, e);
		}
	}

	private void resetData() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	private void processFileData(BufferedReader br) throws IOException, EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicateLocationException, EmsDuplicatePersonException {
		String data;
		while ((data = br.readLine()) != null) {
			String[] datas = dividirEntrada(data.trim());
			for (String linea : datas) {
				processLineData(linea);
			}
		}
	}

	private void processLineData(String linea) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicateLocationException, EmsDuplicatePersonException {
		String[] datos = dividirLineaData(linea);
		if (!datos[0].equals("PERSONA") && !datos[0].equals("LOCALIZACION")) {
			throw new EmsInvalidTypeException();
		}
		if (datos[0].equals("PERSONA")) {
			if (datos.length != Constantes.MAX_DATOS_PERSONA) {
				throw new EmsInvalidNumberOfDataException("El número de datos para PERSONA es menor de 8");
			}
			this.poblacion.addPersona(this.crearPersona(datos));
		} else if (datos[0].equals("LOCALIZACION")) {
			if (datos.length != Constantes.MAX_DATOS_LOCALIZACION) {
				throw new EmsInvalidNumberOfDataException("El número de datos para LOCALIZACION es menor de 6");
			}
			PosicionPersona pp = this.crearPosicionPersona(datos);
			this.localizacion.addLocalizacion(pp);
			this.listaContactos.insertarNodoTemporal(pp);
		}
	}

	public int findPersona(String documento) throws EmsPersonNotFoundException {
		int pos;
		try {
			pos = this.poblacion.findPersona(documento);
			return pos;
		} catch (EmsPersonNotFoundException e) {
			throw new EmsPersonNotFoundException();
		}
	}

	public int findLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {

		int pos;
		try {
			pos = localizacion.findLocalizacion(documento, fecha, hora);
			return pos;
		} catch (EmsLocalizationNotFoundException e) {
			throw new EmsLocalizationNotFoundException();
		}
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException {
		int cont = 0;
		List<PosicionPersona> lista = new ArrayList<PosicionPersona>();
		Iterator<PosicionPersona> it = this.localizacion.getLista().iterator();
		while (it.hasNext()) {
			PosicionPersona pp = it.next();
			if (pp.getDocumento().equals(documento)) {
				cont++;
				lista.add(pp);
			}
		}
		if (cont == 0)
			throw new EmsPersonNotFoundException();
		else
			return lista;
	}

	public boolean delPersona(String documento) throws EmsPersonNotFoundException {
		int cont = 0, pos = -1;
		Iterator<Persona> it = this.poblacion.getLista().iterator();
		while (it.hasNext()) {
			Persona persona = it.next();
			if (persona.getDocumento().equals(documento)) {
				pos = cont;
			}
			cont++;
		}
		if (pos == -1) {
			throw new EmsPersonNotFoundException();
		}
		this.poblacion.getLista().remove(pos);
		return false;
	}

	private String[] dividirEntrada(String input) {
		String cadenas[] = input.split("\\n");
		return cadenas;
	}

	private String[] dividirLineaData(String data) {
		String cadenas[] = data.split("\\;");
		return cadenas;
	}

	private Persona crearPersona(String[] data) {
		Persona persona = new Persona();
		for (int i = 1; i < Constantes.MAX_DATOS_PERSONA; i++) {
			String s = data[i];
			switch (i) {
				case 1:
					persona.setDocumento(s);
					break;
				case 2:
					persona.setNombre(s);
					break;
				case 3:
					persona.setApellidos(s);
					break;
				case 4:
					persona.setEmail(s);
					break;
				case 5:
					persona.setDireccion(s);
					break;
				case 6:
					persona.setCp(s);
					break;
				case 7:
					persona.setFechaNacimiento(parsearFecha(s));
					break;
			}
		}
		return persona;
	}

	private PosicionPersona crearPosicionPersona(String[] data) {
		PosicionPersona posicionPersona = new PosicionPersona();
		String fecha = null, hora;
		float latitud = 0, longitud;
		for (int i = 1; i < Constantes.MAX_DATOS_LOCALIZACION; i++) {
			String s = data[i];
			switch (i) {
				case 1:
					posicionPersona.setDocumento(s);
					break;
				case 2:
					fecha = data[i];
					break;
				case 3:
					hora = data[i];
					posicionPersona.setFechaPosicion(parsearFecha(fecha, hora));
					break;
				case 4:
					latitud = Float.parseFloat(s);
					break;
				case 5:
					longitud = Float.parseFloat(s);
					posicionPersona.setCoordenada(new Coordenada(latitud, longitud));
					break;
			}
		}
		return posicionPersona;
	}

	private FechaHora parsearFecha (String fecha) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		FechaHora fechaHora = new FechaHora(dia, mes, anio, 0, 0);
		return fechaHora;
	}

	private FechaHora parsearFecha (String fecha, String hora) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		int minuto, segundo;
		valores = hora.split("\\:");
		minuto = Integer.parseInt(valores[0]);
		segundo = Integer.parseInt(valores[1]);
		FechaHora fechaHora = new FechaHora(dia, mes, anio, minuto, segundo);
		return fechaHora;
	}
}
