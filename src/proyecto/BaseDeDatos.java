package proyecto;
import java.sql.*;

/** <p>Clase Base de Datos es super clase de las clases que agregan , borran 
 * y buscan cosas en las bases de datos , contiene los comandos basicos , y es
 * tablecen la coneccion</p>*/

public class BaseDeDatos {
	public Connection coneccion=null;
	public Statement instruccion=null;
	public Statement auxiliar=null;
	public String a;
	
	  /**Abre la base de datos con el nombre abreBaseDeDatos 
	   * @param nombreDeLaBaseDeDatos nombre que tiene la base de datos que se abrira
	   * @throws SQLException|ClassNotFoundException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	public void abreBaseDeDatos(String nombreDeLaBaseDeDatos) throws ClassNotFoundException, SQLException{
	    Class.forName("org.sqlite.JDBC");
	      coneccion = DriverManager.getConnection("jdbc:sqlite:"+nombreDeLaBaseDeDatos+".db");
	   
	}
	  /**Cierra la base de datos , primero la instruccion y luego la 
	   * coneccion.
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	public void cierraBaseDeDatos() throws SQLException {
		this.instruccion.close();
		this.coneccion.close();
	
	}
	  /**Regresa el numero de elementos de una tabla
	   * @param tabla  el nombre de la tabla de la que se obtendra el numero
	   * 		       de elementos.
	   * @return int elementos que tiene la tabla
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	

	public int getElementos(String tabla) throws SQLException{
	
		int elementos = 0;
		instruccion=coneccion.createStatement();
	    ResultSet rs = instruccion.executeQuery( "SELECT count(*)  as numeroDeElementos FROM "+tabla+";" );
	      while ( rs.next() ) {
	         elementos=rs.getInt("numeroDeElementos");
	        }
	      
	      return elementos;
	}
	  /**Regresa el indice siguiente disponible para agregar una cancion
	   * @param  tabla de donde se obtendra el siguiente indice disponible
	   * @return int el siguiente indice disponible
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	public int getDondeAgregar(String tabla) throws SQLException{
		
		int elementos = 0;
		instruccion=coneccion.createStatement();
	    ResultSet rs = instruccion.executeQuery( "SELECT MAX(id) AS aDondeAgregar FROM "+tabla+"" );
	      while ( rs.next() ) {
	         elementos=rs.getInt("aDondeAgregar");
	        }
	      
	      return elementos+1;
	}
	  /**Regresa el indice en donde se agregara la cancion/album/autor  en su defecto
	   * en donde esta la cancion
	   * @param string nombre de la tabla en donde se esta haciendo la busqueda
	   * @param nombre nombre del elemento a buscar
	   * @return Regresa el indice en donde se agregara la cancion/album/autor  en su defecto
	   * en donde esta la cancion
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	public int getIndice(String string, String nombre) throws SQLException {
		instruccion = coneccion.createStatement();
		 
		ResultSet rs = instruccion.executeQuery( "SELECT * FROM "+string+" where "+string+" like \"%"+nombre+"%\"" );
		while ( rs.next() ) {
		 return  rs.getInt("id");
		
		}
		return getDondeAgregar(string);
		}
	
	public void eliminaTabla(String nombre) throws SQLException{
		instruccion=coneccion.createStatement();
		String creaTabla= "drop TABLE "+nombre;
		instruccion.executeUpdate(creaTabla);
	}
	

}
