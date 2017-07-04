package proyecto.test;


import proyecto.Cancion;

import org.junit.Assert;
import org.junit.Test;
import proyecto.BaseDeDatos;
import proyecto.CreaTablas;
import proyecto.Busqueda;

/*Clase que testea la clase BaseDeDatos*/

public class TestBusqueda{
	
	   
    /**Prueba que se Busque correctamente en  una base de Datos*/
    
    @Test public void TestBusca() throws Exception {
    Busqueda b = new Busqueda();
    b.busquedas[1]="Queen";
  
    b.abreBaseDeDatos("musica");
    b.busca();
    Assert.assertTrue(b.resultadoDeBusqueda.size()==8);
    }
    
 
}