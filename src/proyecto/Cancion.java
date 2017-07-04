package proyecto;

import java.io.File;
import java.io.IOException;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
/** <p>Clase Cancion que tiene los atributos de una cancion , distintos constructores ,y los getters 
 * y setters/p>*/

public class Cancion{
	
	public String nombre;
	public String autor;
	public String album;
	public String anio;
	public String genero;
	public String indice;
	
	  /**Regresa la representacion en string de una cancion
	  @return la representacion en strings de una cadena*/
	
	public String toString(){
		return indice+" "+nombre+" "+autor+" "+album+" "+anio+" "+genero;
	}
	
	  /**Constructor que recibe los parametros de la informacion de una cancion 
	   * @param indice indice de la cancion
	   * @param nombre nombre de la cancion
	   * @param autor autor de la cancion
	   * @param album album de la cancion
	   * @param anio anio de la cancion
	   * @param genero genero de la cancion
	   * @throws Exception se lanza una excepcion si se es 
	   * 		 pertinente*/
	
	public Cancion(String indice ,String nombre, String autor,String album,String anio, String genero) throws Exception{
		
		 this.indice = indice.equals("")  ?"sinIndice" :indice;
		 this.nombre = nombre.equals("")  ?"sinNombre" :nombre;
		 this.autor  = autor.equals ("")  ?"sinAutor"  :autor;
		 this.album  = album.equals ("")  ?"sinAlbum"  :album;
		 this.anio  =  anio.equals  ("")  ?"sinAnio"   :anio;
		 this.genero = genero.equals("")  ?"sinGenero" :genero;
	}
	
	 /**Constructor que recibe como parametro  un archivo de musica
	  * y extra la informacion de el
	   * @param a el archivo de musica
	   * @throws IOException, TagException se lanza una excepcion si se es 
	   * 		 pertinente*/
	
	public Cancion(File a) throws IOException, TagException{
		try{
		MP3File cancion=new MP3File(a);
		AbstractID3v2 etiqueta = cancion.getID3v2Tag();

			
		  String nombre=etiqueta.getSongTitle();
		  this.nombre = nombre.equals("")  ?incluye(a.getName()):normaliza(nombre);
		  String autor=  etiqueta.getLeadArtist();
		  this.autor = autor.equals("")  ?"sinAutor":normaliza(autor);
		  String album = etiqueta.getAlbumTitle();
		  this.album = album.equals("")  ?"sinAlbum":normaliza(album);
		  String anio=  etiqueta.getYearReleased();
		  this.anio = anio.equals("")  ?"sinAnio":normaliza(anio);
		  String genero=  etiqueta.getSongGenre();
		  this.genero = genero.equals("")  ?"sinGenero":normaliza(genero);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("La cancion "+a.getName()+"no se pudo agregar correctamente");
		
		}
		
	}
	  /**Quita el .mp3 de un String  
	   * @param string a quitar el .mp3
	   * @return String sin el .mp3 */
	private String incluye(String name) {
	
	return name.replaceAll(".mp3", "");
	}
	private static String normaliza(String resultado) {
	if(resultado.contains("??")){
		resultado=resultado.replaceAll("??","");
	char [] auxiliar= resultado.toCharArray();
	

	resultado = "";
	for(int i =0;i<auxiliar.length;i+=2){
		resultado+=String.valueOf(auxiliar[i]);
		
	 }
	}
	return resultado.replaceAll("'","");
  }
	  /**Getter de nombre
	   @param nombre
	   */
	
	 public String getNombre() {
         return nombre;
     }

	  /**Establece como nombre el nuevo nombre
      * @param nombre */
     public void setNombre(String nombre) {
    	 
         this.nombre=!nombre.equals("")?nombre:"sinNombre";
     }
     
     /**Getter de autor
      * @return autor */
     
     public String getAutor() {
         return autor;
     }
     
     /**Establece como autor el nuevo autor
      * @param autor */
     
     public void setAutor(String autor) {
    	  this.autor=!autor.equals("")?autor:"sinAutor";
     }
     
     /**Getter de album
	   @return album
	   */
     public String getAlbum() {
         return album;
     }
     
     /**Establece como album el nuevo album
      * @param album*/
     public void setAlbum(String album) {
    	 this.album=!album.equals("")?album:"sinAlbum";
     }
     /**Getter de anio
	   @return anio
	   */
     
     public String getAnio() {
         return anio;
     }
     
     /**Establece como anio el nuevo anio
      * @param anio*/

     public void setAnio(String anio) {
    	  this.anio=!anio.equals("")?anio:"sinAnio";
     }
     
     /**Getter de genero
	   @return genero
	   */
     public String getGenero() {
         return genero;
     }
     
     /**Establece como genero el nuevo genero
      * @param genero*/


     public void setGenero(String genero) {
    	  this.genero=!genero.equals("")?genero:"sinGenero";
     }

     

   

}
