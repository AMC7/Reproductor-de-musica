package proyecto;		 
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.*;
import org.farng.mp3.*;

/** <p>Clase que agrega canciones , ya sea con los strings que representan
 * los datos , extrayendo la informacion de un archivo , ya sea directamente del
 * archivo o de una carpeta con archivos de musica</p>*/

public class AgregaCanciones extends BaseDeDatos{
	
	 File [] arregloDeCanciones;//Todos los archivos mp3 de un drectorio
	 
	 
	  
	 /**Constructor sin parametros , abre la base de datos musica , para su posterior
	   *  uso.
	   *  @throws ClassNotFoundException o SQLException cuando se pertinente*/
	 	
	  public AgregaCanciones() throws ClassNotFoundException, SQLException {
		  this.abreBaseDeDatos("musica");
	}
	  
	  /**creaArregloDeCanciones , dado un directorio filtra todos aquellos archivos
	   * mp3
	   * @param string La direccion del directorio de donde se sacaran los archivos
	   * @return File[] arreglo con todos los archivos de musica*/

	public File[] creaArregloDeCanciones(String string) {

			File carpetaDeCanciones=new File(string);
			FileFilter aceptaCanciones=new FileFilter(){

				@Override
				public boolean accept(File file) {
					if(file.getName().contains(".mp3")){
						return true;
					}
					return false;
				}
				
			};
			return carpetaDeCanciones.listFiles(aceptaCanciones);


		}

	

	
  public static void main( String args[] ) throws IOException, TagException, ClassNotFoundException, SQLException{
	  AgregaCanciones agregador= new AgregaCanciones();
	    
     
	  

 
    agregador.cierraBaseDeDatos();
 
  
  }


  /**Agrega a la base de datos agrega a la Base de Datos  los datos
   * de la cancion que se encuentra en direccion string 
   * @param string La direccion en donde esta el archivo*
   * @throws SQLException se lanza una excepcion sql si se es 
   * 		 pertinente*/

  
  public void agrega(String string) throws SQLException {
	this.arregloDeCanciones=this.creaArregloDeCanciones(string);
 	for(File direccion:this.arregloDeCanciones){
  		Cancion cancion = null;
  		this.instruccion = this.coneccion.createStatement();
		try {
  		 cancion=new Cancion(direccion); 
  		 System.out.println(cancion.nombre);
		}catch(Exception e){
			
		}
		if(cancion!=null){
		  this.agregaCancion(cancion);
		}
		
		  
		}

	
}
  
  /**Agrega a la la tabla cancion ,album autor , y en la tabla de relaciones
   * autorCancion , autorAlbum la informacion de la cancion
   * @param cancion la cancion de la que se obtiene la informacion
   * @throws SQLException se lanza una excepcion sql si se es 
   * 		 pertinente*/

  public  void agregaCancion(Cancion cancion) throws SQLException {
 
	 int a= getElementos("cancion");
	
	 int numeroDeCancion=insertaEnTabla(cancion,0);
	 int numeroDeAlbum=insertaEnTabla(cancion,1);
	 int numeroDeAutor=insertaEnTabla(cancion,2);

 	int b=getElementos("cancion");
 	
 	if(a!=b){
 		insertaEnTablaDeRelaciones(numeroDeAutor,numeroDeCancion,1);
 		insertaEnTablaDeRelaciones(numeroDeAlbum,numeroDeCancion,2);
 	}
 }

  /**Agrega a la tabla de relaciones autorCancion o autorAlbum la 
   * informacion de la cancion
   * @param a indice de autor , o del album
   * @param b indice de la cancion
   * @param 1 si se agregara en autorcancion, 2 si se agregara en albumcancion
   * @throws SQLException se lanza una excepcion sql si se es 
   * 		 pertinente*/


  private void insertaEnTablaDeRelaciones(int a, int b, int caso) throws SQLException {
	instruccion=coneccion.createStatement();
	String s="";
	
	switch(caso){
	
	case 1:

		s = "INSERT INTO AUTORCANCION (AUTOR,CANCION)"
				+ " VALUES ("+a+","+b+");"; 
		break;
	
	case 2:
	   s = "INSERT INTO ALBUMCANCION (ALBUM,CANCION)"
				+ " VALUES ("+a+","+b+");"; 
	}

	
 	 instruccion.executeUpdate(s); 
 
	
}






  /**Agrega a la la tabla cancion ,album autor , y en la tabla de relaciones
   * autorCancion , autorAlbum la informacion de la cancion
   * @param cancion la cancion de la que se obtiene la informacion
   * @param caso que es en que tabla se agregara los elementos de la cancion
   * @return int , en donde se agregara o en donde esta ubicada la cancion 
   * @throws SQLException se lanza una excepcion sql si se es 
   * 		 pertinente*/
  
  private int insertaEnTabla(Cancion cancion,int caso) throws SQLException {
	instruccion=coneccion.createStatement();
	int i=0;
	String s="";
	switch(caso){
	case 0:
	 i=getIndice("CANCION",cancion.nombre);
	 
	 if(i==getDondeAgregar("CANCION")){//Si no esta lo insertas
	 s = "INSERT INTO CANCION (ID,CANCION,GENERO)"
			+ " VALUES ("+i+",'"+cancion.nombre+"','"+cancion.genero+"');";
	 }
	 
	break;
	case 1:

	i=getIndice("ALBUM",cancion.album);
	if(i==getDondeAgregar("ALBUM")){
	s = "INSERT INTO ALBUM (ID,ALBUM,ANIO)"
				+ " VALUES ("+i+",'"+cancion.album+"','"+cancion.anio+"');"; 
	}
	break;
	case 2:
		 i=getIndice("AUTOR",cancion.autor);
	
		 if(i==getDondeAgregar("AUTOR")){
	     s = "INSERT INTO AUTOR (ID,AUTOR)"
				+ " VALUES ("+i+",'"+cancion.autor+"');"; 
		 }
	break; 
	}

 	 instruccion.executeUpdate(s); 
 	return i;
	
}

















}
