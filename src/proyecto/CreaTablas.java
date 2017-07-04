package proyecto;
import java.sql.*;
/** <p>Clase que crea las tablas de la base de Datos Musica extiende BaseDeDatos/p>*/

public class CreaTablas extends BaseDeDatos{
	
	  /**Agrega a la base de datos la tabla con la informacion de los parametros
	   * @param string La direccion en donde esta el archivo
	   * @param string La direccion en donde esta el archivo
	   * @throws SQLException se lanza una excepcion sql si se es 
	   * 		 pertinente*/
	
	public void creaTabla(String nombre,String columnas) throws SQLException{
		instruccion=coneccion.createStatement();
		String creaTabla= "CREATE TABLE "+nombre+"("+columnas+")";
		instruccion.executeUpdate(creaTabla);
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		CreaTablas ini=new CreaTablas();
		ini.abreBaseDeDatos("musica");
		ini.creaTabla("AUTOR", " ID INT PRIMARY KEY NOT NULL , AUTOR TEXT NOT NULL");
		ini.creaTabla("ALBUM", " ID INT PRIMARY KEY NOT NULL , ALBUM TEXT NOT NULL, ANIO  TEXT NOT NULL");
		ini.creaTabla("CANCION","ID INT PRIMARY KEY NOT NULL ,CANCION TEXT NOT NULL , GENERO TEXT NOT NULL");
		
	
		ini.creaTabla("AUTORCANCION","AUTOR INT , CANCION INT");
		ini.creaTabla("ALBUMCANCION","ALBUM INT , CANCION INT");
		
		ini.cierraBaseDeDatos();
	}

}
    
   
