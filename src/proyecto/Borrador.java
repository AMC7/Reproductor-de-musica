package proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/** <p>Clase que borra las canciones dados los parametros en el String 
 * a Borrar/p>*/

public class Borrador extends BaseDeDatos{
	
	public String[] aBorrar= new String[5];//Los parametros de la busqueda
	
	/**Borra de la base de datos todas las canciones que coinciden con
	 * los parametros de aBorrar
	  * @throws Exception se lanza una excepcion si se es pertinente*/
	public void borra() throws Exception{
	
	Busqueda a = new Busqueda();
	a.abreBaseDeDatos("musica");
	a.busquedas=aBorrar;
	a.busca();
		
	if(!a.resultadoDeBusqueda.isEmpty()){
		for(Cancion cancion :a.resultadoDeBusqueda){
			borraCancion(cancion);
		}
	 }
	}
	
	
	/**Borra de las distintas tablas que componen a la base de datos 
	 * la cancion que recibe como parametro.
	 * @param cancion la cancion que se va a eliminar
	 * @throws SQLException se lanza una excepcion sql si se es 
     * 		 pertinente*/
	
	public void borraCancion(Cancion cancion) throws SQLException {
	
		instruccion=coneccion.createStatement();
		String string="DELETE FROM CANCION WHERE ID="+cancion.indice;
		instruccion.executeUpdate(string); 
		
		 string="DELETE FROM AUTORCANCION WHERE CANCION="+cancion.indice;
		 instruccion.executeUpdate(string); 
		 int autor =getNumeroDeAutor(instruccion, cancion.autor);
	     int numeroDeCancionesDeAutor= getNumeroDeCancionesDeAutor(instruccion,autor);
		 if(numeroDeCancionesDeAutor==0){
			 string="DELETE FROM AUTOR WHERE ID="+autor;
			 instruccion.executeUpdate(string);
		 }
	   
		 string="DELETE FROM ALBUMCANCION WHERE CANCION ="+cancion.indice;
	  	 instruccion.executeUpdate(string); 
		 int album =getNumeroDeAlbum(instruccion, cancion.album);
	     int numeroDeCancionesDeAlbum= getNumeroDeCancionesDeAlbum(instruccion,album);
		 if(numeroDeCancionesDeAlbum==0){
			string="DELETE FROM ALBUM WHERE ID="+album;
			instruccion.executeUpdate(string);
		 }
		
		
	}



	/**Regresa si hay canciones en un album
	   * @param instruccion el statement que se ejecutara
	   * @param album el album del que se obtendra el numero de elementos
	   * @return 1 si hay canciones de ese album , 0 en otro caso
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	

	private int getNumeroDeCancionesDeAlbum(Statement instruccion, int album) throws SQLException {

		 ResultSet rs=instruccion.executeQuery("SELECT * from autorcancion where autor = "+album);
		 int numero= 0;
		while(rs.next()){
			return 1;
			// ++numero;
		 }
		return numero;

	}


	/**Regresa el indice de un album album 
	   * @param instruccion el statement que se ejecutara
	   * @param album el album del que se obtendra el indices
	   * @return int el indice del album
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	private int getNumeroDeAlbum(Statement instruccion, String album) throws SQLException {
		ResultSet rs=instruccion.executeQuery("SELECT * from album where album.album like '"+album+"'" );
		 int numeroDeAutor = 0;
		while(rs.next()){
			 numeroDeAutor=rs.getInt("id");
			//return 1;
		 }
		return numeroDeAutor;
	}

	/**Regresa si hay canciones de un autor
	   * @param instruccion el statement que se ejecutara
	   * @param autor el autor del que se obtendra si hay canciones de el
	   * @return 1 si hay canciones de ese autor , 0 en otro caso
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	

	private int getNumeroDeCancionesDeAutor(Statement instruccion, int numeroDeAutor) throws SQLException {

		 ResultSet rs=instruccion.executeQuery("SELECT * from autorcancion where autor = "+numeroDeAutor);
		 int numero= 0;
		while(rs.next()){
			return 1;
			 //++numero;
		 }
		return numero;
	}
	/**Regresa el indice de un autor autor 
	   * @param instruccion el statement que se ejecutara
	   * @param autor el autor del que se obtendra el indices
	   * @return int el indice del autor
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/

	private int getNumeroDeAutor(Statement instruccion, String autor) throws SQLException {

		 ResultSet rs=instruccion.executeQuery("SELECT * from autor where autor.autor like '"+autor+"'" );
		 int numeroDeAutor = 0;
		while(rs.next()){
			 numeroDeAutor=rs.getInt("id");
		 }
		return numeroDeAutor;

	}



	
	
}
