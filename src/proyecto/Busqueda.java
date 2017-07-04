package proyecto;


import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** <p>Clase que busca canciones de una base de Datos extiende BaseDeDatos/p>*/

public class Busqueda extends BaseDeDatos{
	
	public String[]busquedas= new String[5];
	
	
	public ObservableList<Cancion> resultadoDeBusqueda =FXCollections.observableArrayList();;

	  /**Busca en la base de datos las canciones que coincidan con las condiciones de busquedas
	   * @throws Exception se lanza una excepcion sql si se es pertinente*/
	
	public void busca() throws Exception{
		
		String s=creaCondicionales(busquedas);
		instruccion=coneccion.createStatement();
		
		String elementosDeTablas="autor.autor"
		  		+ " ,album.album , album.anio ,cancion.cancion ,cancion.genero"
		  		+ " ,autorcancion.cancion , autorcancion.autor ,albumcancion.album , "
		  		+ "albumcancion.cancion ,cancion.id ";
		
		String tablas ="autor , cancion ,autorcancion, albumcancion ,album ";
		
		String condiciones=" autorcancion.autor=autor.id and autorcancion.cancion=cancion.id  "
		  		+ "and albumcancion.album=album.id and albumcancion.cancion=cancion.id "+s;
		
		
	  ResultSet rs=instruccion.executeQuery("SELECT "+elementosDeTablas+"from "+tablas+"where "+condiciones );
		while(rs.next()){
			String indice=rs.getString("id");
			String nombre=rs.getString("cancion");
			String autor=rs.getString("autor");
			String genero=rs.getString("genero");
			String anio=rs.getString("anio");
			String album=rs.getString("album");
			
			
			Cancion a = new Cancion(indice,nombre,autor,genero,anio,album);
			resultadoDeBusqueda.add(a);
			
		}
		
	
		
	}
	
	  /**Regresa un string con las condiciones de busquedas 
	   * @param busquedas  las condiciones a agregar*/
	
	private String creaCondicionales(String[] busquedas) {
		String a=""; 
		
		if(busquedas[0]!=null){
			a+=" and cancion.cancion like '"+busquedas[0]+"'";
		}
		if(busquedas[1]!=null){
			a+=" and autor.autor like '"+busquedas[1]+"'";
		}
		if(busquedas[2]!=null){
			a+=" and album.album like '"+busquedas[2]+"'";
		}
		if(busquedas[3]!=null){
			a+=" and album.anio "+busquedas[3];
		}
		if(busquedas[4]!=null){
			a+=" and cancion.genero like '"+busquedas[4]+"'";
		}
		return a;
		
	}

	public static void main(String[]args) throws Exception{
		
		Busqueda busqueda = new Busqueda();
		busqueda.busquedas[1]="Lose Yourself";
	
			
	
		busqueda.abreBaseDeDatos("musica");
		busqueda.busca();
		System.out.println(busqueda.resultadoDeBusqueda);
		busqueda.cierraBaseDeDatos();
	}
	
}
