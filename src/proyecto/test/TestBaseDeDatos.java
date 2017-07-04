package proyecto.test;


import proyecto.Cancion;

import org.junit.Assert;
import org.junit.Test;
import proyecto.BaseDeDatos;
import proyecto.CreaTablas;

/*Clase que testea la clase BaseDeDat0s*/

public class TestBaseDeDatos{
	
	   
    /**Prueba que abra una base de Datos*/
    
    @Test public void TestAbreBaseDeDatos() throws Exception {
       
    BaseDeDatos baseDeDatos = new BaseDeDatos();
    baseDeDatos.abreBaseDeDatos("pruebas");
    Assert.assertTrue(true);//Si llega hasta aqui regresa true y que se cree la coneccion bien
    }
    
   
    
	/**Prueba que se cierra la base de Datos*/
    @Test public void TestCierraBaseDeDatos() throws Exception {
	    BaseDeDatos baseDeDatos = new BaseDeDatos();
	    baseDeDatos.abreBaseDeDatos("pruebas");
	    baseDeDatos.instruccion=baseDeDatos.coneccion.createStatement();
	    baseDeDatos.cierraBaseDeDatos();
	}
    
    /**Prueba que se obtenga correctamente el numero de elementos*/
    
    @Test public void TestGetElementos() throws Exception {
    	   CreaTablas baseDeDatos = new CreaTablas();
    	     baseDeDatos.abreBaseDeDatos("pruebas");
    	
    	  
    	     baseDeDatos.creaTabla("alumno" ,"alumno text not null");
    	     
    	     baseDeDatos.instruccion=baseDeDatos.coneccion.createStatement();
    	     String s = "INSERT INTO alumno (alumno) VALUES ('Felipe Masa');";
    	     baseDeDatos.instruccion.executeUpdate(s);
    	     
    	     int i = baseDeDatos.getElementos("alumno");
    	   
    	     Assert.assertTrue(i==1);
    	     baseDeDatos.eliminaTabla("alumno");
     
     }
    
    /**Prueba que se obtenga correctamente el siguiente id disponible para agregara*/
    @Test public void TestADondeAgregar() throws Exception {
     
    	 CreaTablas baseDeDatos = new CreaTablas();
	     baseDeDatos.abreBaseDeDatos("pruebas");
	
	  
	     baseDeDatos.creaTabla("alumno" ,"id int primary key not null ,alumno text not null");
	     
	     baseDeDatos.instruccion=baseDeDatos.coneccion.createStatement();
	     String s = "INSERT INTO alumno (id,alumno) VALUES (1,'Felipe Masa');";
	     baseDeDatos.instruccion.executeUpdate(s);
	      s = "INSERT INTO alumno (id,alumno) VALUES (2,'Cristiano Ronaldo');";
	     baseDeDatos.instruccion.executeUpdate(s);
	     s = "INSERT INTO alumno (id,alumno) VALUES (3,'Belinda dos santos');";
	     baseDeDatos.instruccion.executeUpdate(s);
	     
	     s = "delete from alumno where id=1;";
	     baseDeDatos.instruccion.executeUpdate(s);
	     
	     int i = baseDeDatos.getDondeAgregar("alumno");
	     Assert.assertTrue(i==4);
	     
	     
	     s = "INSERT INTO alumno (id,alumno) VALUES (4,'Belinda dos santos');";
	     baseDeDatos.instruccion.executeUpdate(s);
	     
	     i = baseDeDatos.getDondeAgregar("alumno");
	     Assert.assertTrue(i==5);
	     
	     
	     s = "delete from alumno where id=4;";
	     baseDeDatos.instruccion.executeUpdate(s);
	    
	     i = baseDeDatos.getDondeAgregar("alumno");
	     Assert.assertTrue(i==4);
	     
	     baseDeDatos.eliminaTabla("alumno");
    }
        

       
       
    /**Prueba que se elimine correctamente una tabla*/
    @Test public void TestEliminaTabla() throws Exception {
        
     CreaTablas baseDeDatos = new CreaTablas();
     
    
     baseDeDatos.abreBaseDeDatos("pruebas");

     baseDeDatos.creaTabla("alumno" ,"alumno text not null");
     baseDeDatos.eliminaTabla("alumno");
     
     try{
    	 baseDeDatos.getElementos("elementos");
     }catch(Exception e){
    	 Assert.assertTrue(true);
     }
     }
}