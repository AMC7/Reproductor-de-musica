package proyecto.test;


import proyecto.Cancion;

import org.junit.Assert;
import org.junit.Test;


/*Clase que testea la clase cancion*/

public class TestCancion{
	
	Cancion cancion ;

	   
    /**Prueba que la suma se realice correctamente*/
    
    @Test public void TestConstructor() throws Exception {
        cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");

    	Assert.assertTrue(cancion.indice.equals("1"));
    	Assert.assertTrue(cancion.nombre.equals("Beat It"));
    	Assert.assertTrue(cancion.autor.equals("Michael Jackson"));
    	Assert.assertTrue(cancion.album.equals("Thriller"));
    	Assert.assertTrue(cancion.anio.equals("1985"));
    	Assert.assertTrue(cancion.genero.equals("Pop"));
    	
    	cancion= new Cancion("","","","","","");
    	Assert.assertTrue(cancion.indice.equals("sinIndice"));
    	Assert.assertTrue(cancion.nombre.equals("sinNombre"));
    	Assert.assertTrue(cancion.autor.equals("sinAutor"));
    	Assert.assertTrue(cancion.album.equals("sinAlbum"));
    	Assert.assertTrue(cancion.anio.equals("sinAnio"));
    	Assert.assertTrue(cancion.genero.equals("sinGenero"));
 
    }
    
  /*Prueba que la suma se realice correctamente*/
    
    @Test public void TestIncluye() throws Exception {
      Assert.assertTrue(true);
    }
    
    /**Prueba que se obtenga la cancion correctamente  */
    

    @Test public void TestGetNombre() throws Exception{
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
    	 Assert.assertTrue(cancion.getNombre().equals("Beat It"));
       
    }

	  /**Prueba que se establezca como nombre el nuevo nombre correctamente*/
    
    @Test  public void TestSetNombre() throws Exception {
       cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
       cancion.setNombre("");
       Assert.assertTrue(cancion.nombre.equals("sinNombre"));
       cancion.setNombre("Beat It");
       Assert.assertTrue(cancion.nombre.equals("Beat It"));
    }
    
    /**Prueba que se obtenga el  autor correctamente*/
    
    @Test public void TestGetAutor() throws Exception{
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
    	 Assert.assertTrue(cancion.getAutor().equals("Michael Jackson"));
       
    }
    
    /**Prueba que se establezca como autor el nuevo autor correctamente*/
    
    @Test public void TestSetAutor() throws Exception {
    	
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
         cancion.setAutor("");
         Assert.assertTrue(cancion.getAutor().equals("sinAutor"));
         cancion.setAutor("Michael Jackson");
         Assert.assertTrue(cancion.getAutor().equals("Michael Jackson"));
     
    }
    
    /**Prueba que se obtenga el album correctamente*/
    @Test public void TestGetAlbum() throws Exception {
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
    	 Assert.assertTrue(cancion.getAlbum().equals("Thriller"));
    }
    
    /**Prueba que se establezca como album el nuevo album correctamente*/
    @Test public void TestSetAlbum() throws Exception {
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
         cancion.setAlbum("");
         Assert.assertTrue(cancion.getAlbum().equals("sinAlbum"));
         cancion.setAlbum("a");
         Assert.assertTrue(cancion.getAlbum().equals("a"));
     
   
    }
    /**Prueba que se obtenga el anio correctamente*/
    @Test public void TestGetAnio() throws Exception {
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
    	 Assert.assertTrue(cancion.getAnio().equals("1985"));

    }
    
    /**Prueba que se establezca como anio el nuevo anio correctamente*/

    @Test public void TestSetAnio() throws Exception {
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
         cancion.setAlbum("");
         Assert.assertTrue(cancion.getAlbum().equals("sinAlbum"));
         cancion.setAlbum("a");
         Assert.assertTrue(cancion.getAlbum().equals("a"));
    }
    
    /**Prueba que se obtenga el genero correctamente*/
    
    @Test public void TestGetGenero() throws Exception{
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
    	 Assert.assertTrue(cancion.getGenero().equals("Pop"));

    }
    
    /**Prueba que se establezca como genero el nuevo genero correctamente*/


    @Test public void TestSetGenero() throws Exception{
    	 cancion=new Cancion("1","Beat It","Michael Jackson","Thriller","1985","Pop");
         cancion.setGenero("");
         Assert.assertTrue(cancion.getGenero().equals("sinGenero"));
         cancion.setGenero("a");
         Assert.assertTrue(cancion.getGenero().equals("a"));
    }

    
 
    
	
	
	
}