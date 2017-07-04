package proyecto.test;


import proyecto.Cancion;

import org.junit.Assert;
import org.junit.Test;
import proyecto.BaseDeDatos;
import proyecto.CreaTablas;
import proyecto.AgregaCanciones;

/*Clase que testea la clase BaseDeDat0s*/

public class TestAgregaCanciones{
	
	   
    /**Prueba que abra una base de Datos*/
    
    @Test public void TestAgregaCancion() throws Exception {
    	CreaTablas ini=new CreaTablas();
		ini.abreBaseDeDatos("pruebas");
		ini.creaTabla("AUTOR", " ID INT PRIMARY KEY NOT NULL , AUTOR TEXT NOT NULL");
		ini.creaTabla("ALBUM", " ID INT PRIMARY KEY NOT NULL , ALBUM TEXT NOT NULL, ANIO  TEXT NOT NULL");
		ini.creaTabla("CANCION","ID INT PRIMARY KEY NOT NULL ,CANCION TEXT NOT NULL , GENERO TEXT NOT NULL");
		
	
		ini.creaTabla("AUTORCANCION","AUTOR INT , CANCION INT");
		ini.creaTabla("ALBUMCANCION","ALBUM INT , CANCION INT");
		
		
		
		ini.cierraBaseDeDatos();
		
		AgregaCanciones agrega = new AgregaCanciones();
		agrega.abreBaseDeDatos("pruebas");
		Cancion cancion = new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
		agrega.agregaCancion(cancion);
	
		Assert.assertTrue(true);
	
		agrega.eliminaTabla("AUTOR");
		agrega.eliminaTabla("ALBUM");
		agrega.eliminaTabla("CANCION");
		agrega.eliminaTabla("AUTORCANCION");
		agrega.eliminaTabla("ALBUMCANCION");
    }
    
   
}