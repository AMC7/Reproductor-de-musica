package proyecto.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import proyecto.BaseDeDatos;
import proyecto.CreaTablas;

/*Clase que testea la clase BaseDeDat0s*/

public class TestCreaTablas{

    /**Prueba que se creen tablas */
    
    @Test public void TestCreaTabla() throws Exception {
    	CreaTablas a = new CreaTablas();
    	
    	a.abreBaseDeDatos("pruebas");
    	
	    a.creaTabla ("alumno", " ID INT PRIMARY KEY NOT NULL , NOMBRE TEXT NOT NULL");
	a.getElementos("alumno");
	a.eliminaTabla("alumno");
    	
    
    }
   
   
    
    
    
    	
}