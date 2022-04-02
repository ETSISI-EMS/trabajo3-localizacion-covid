package com.practica.ems.covid;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.genericas.FechaHora;

public class Principal {
	
	
	public static void main(String[] args) throws EmsDuplicatePersonException, EmsDuplicateLocationException, EmsInvalidTypeException, EmsInvalidNumberOfDataException {
		String test_data_str = "PERSONA;87654321K;Jessica;Diaz;jessica.diaz@ems.com;La calle de jessica, 33;28033;25/01/1980\n" +
	            "PERSONA;98765432J;Angel;Panizo;angel.panizo@ems.com;La calle de Angel, 46;28871;12/01/1995\n" +
	            "LOCALIZACION;87654321K;25/10/2021;23:41;41.3870;2.1698\n" +
	            "LOCALIZACION;87654321K;25/10/2021;23:45;41.3870;2.1695\n" +
	            "LOCALIZACION;98765432J;25/10/2021;23:55;41.3871;2.1697\n" +
	            "LOCALIZACION;87654321K;25/10/2021;23:55;41.3871;2.1697\n";
		ContactosCovid contactosCovid = new ContactosCovid();
		contactosCovid.loadDataFile("datos2.txt", false);
		System.out.println(contactosCovid.getLocalizacion().toString());
		System.out.println(contactosCovid.getPoblacion().toString());
		//contactosCovid.loadData("LOCALIZACION;12345678J;16/05/2021;20:45;54.3890;28.1698\n", false);
		System.out.println(contactosCovid.getListaContactos().tamanioLista());
		System.out.println(contactosCovid.getListaContactos().getPrimerNodo());
		System.out.println(contactosCovid.getListaContactos());
		FechaHora ini = new FechaHora(25,5,2021,16,30);
		FechaHora fin = new FechaHora(25,5,2021,16,30);
		
	}
}
