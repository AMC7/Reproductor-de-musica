package proyecto.test;


import proyecto.Cancion;

import org.junit.Assert;
import org.junit.Test;
import proyecto.BaseDeDatos;
import proyecto.CreaTablas;
import proyecto.Busqueda;
import proyecto.Borrador;
import proyecto.Busqueda;
import proyecto.AgregaCanciones;

/*Clase que testea la clase BaseDeDatos*/

public class TestBorrador{
	
	   
    /**Prueba que se Borre correctamente en  una base de Datos*/
    
    @Test public void TestBorra() throws Exception {
    AgregaCanciones a = new AgregaCanciones();
    a.abreBaseDeDatos("musica");
    a.agregaCancion(new Cancion("","","","","",""));
    a.cierraBaseDeDatos();
    
    Borrador b= new Borrador();
   
    b.aBorrar[0]="sinNombre";
    b.abreBaseDeDatos("musica");
    int i =b.getElementos("cancion"); 
    b.borra();
    int j=b.getElementos("cancion"); 
    Assert.assertTrue(j+1==i);
    b.cierraBaseDeDatos();
    
 }
    
 
}