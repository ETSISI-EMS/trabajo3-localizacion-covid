package com.practica.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica.ems.covid.ContactosCovid;
import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;

public class Test_3 {
	private static ContactosCovid contactosCovid;

	@BeforeEach
	void setUp() {		
		contactosCovid = new ContactosCovid();
		contactosCovid.loadDataFile("datos2.txt", false);
	}
	@DisplayName("Comprobamos que se genera la lista de contactos")
	@Test
	void test_1 () {
		assertNotNull(contactosCovid.getListaContactos());		
	}
	
	@DisplayName("Comprobamos la primera localización del fichero")
	@Test
	void test_2 () {
		assertEquals("25/05/2021;16:30", contactosCovid.getListaContactos().getPrimerNodo());
	}
	
	@DisplayName("Comprobamos el número total de nodos temporales")
	@Test
	void test_3 () {
		assertEquals(4, contactosCovid.getListaContactos().tamanioLista());
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal al principio de la lista")
	@Test
	void test_4 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;11:01;44.3870;2.4698", false);
		assertEquals("25/05/2021;11:01", contactosCovid.getListaContactos().getPrimerNodo());
	}
	
	@DisplayName("Al aniadir un nuevo nodo al principio la lista temporal aumenta en uno")
	@Test
	void test_5 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		int tam_ant = contactosCovid.getListaContactos().tamanioLista();
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;11:01;44.3870;2.4698", false);
		assertEquals(contactosCovid.getListaContactos().tamanioLista(), (tam_ant+1));		
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal al final de la lista")
	@Test
	void test_6 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;19:01;44.3870;2.4698", false);
		assertEquals("25/05/2021;16:30 25/05/2021;16:36 25/05/2021;17:18 25/05/2021;18:01 25/05/2021;19:01", contactosCovid.getListaContactos().toString());
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal en medio de la lista")
	@Test
	void test_7 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;12121212R;25/05/2021;17:05;43.3870;2.3698", false);
		assertEquals("25/05/2021;16:30 25/05/2021;16:36 25/05/2021;17:05 25/05/2021;17:18 25/05/2021;18:01", contactosCovid.getListaContactos().toString());
	}
	
	@DisplayName("Comprobamos el numero de personas entre dos instantes temporales")
	@Test
	void test_8 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		FechaHora ini = new FechaHora(25,5,2021,16,30);
		FechaHora fin = new FechaHora(25,5,2021,16,30);
		assertEquals(4, contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin));
	}
	
	@DisplayName("Comprobamos el numero de personas entre dos instantes temporales")
	@Test
	void test_9 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		FechaHora ini = new FechaHora(25,5,2021,16,36);
		FechaHora fin = new FechaHora(25,5,2021,16,36);
		assertEquals(4, contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin));
	}
	
	@DisplayName("Comprobamos el numero de nodos coordenada entre dos instantes temporales")
	@Test
	void test_10 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		FechaHora ini = new FechaHora(25,5,2021,16,36);
		FechaHora fin = new FechaHora(25,5,2021,16,36);
		assertEquals(3, contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin));
	}
	
	@DisplayName("Comprobamos el numero de nodos coordenada y el número de personas entre dos instantes temporales")
	@Test
	void test_11 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		FechaHora ini = new FechaHora(25,5,2021,16,30);
		FechaHora fin = new FechaHora(25,5,2021,18,01);
		assertEquals(10, contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin));
		assertEquals(16, contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin));
	}
	
	@DisplayName("Aniadimos un nuevo nodo coordenadas en una nodo temporal que existe")
	@Test
	void test_12 () throws EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException {
		FechaHora ini = new FechaHora(25,5,2021,16,36);
		FechaHora fin = new FechaHora(25,5,2021,16,36);
		assertEquals(3, contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin));
		contactosCovid.loadData("LOCALIZACION;99998888X;25/05/2021;16:36;54.2256;32.1234", false);
		assertEquals(4, contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin));
	}
}
