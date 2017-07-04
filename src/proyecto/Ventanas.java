package proyecto;


import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/** <p>Clase en que se incluye la representacion grafica de la base de Datos , extiende Application/p>*/
public class Ventanas extends Application {
	
	public TableView<Cancion> tabla = new TableView<Cancion>();
	public ObservableList<Cancion> data =FXCollections.observableArrayList();;
	public AccionesEnJavaFX acciones = new AccionesEnJavaFX(); 
   
	

	 /**Agrega a la vetana el titulo , las tablas y la interfaz con el usuario 
     *  @param stage stage de la ventana
	*/
	
	@Override
    public void start(Stage stage) throws Exception {
        
        acciones.ajustesBasicosDeStage(stage,"TonioTunes",967,600);
     
        Group root =new Group();
        BorderPane border = new BorderPane();
        data=poblaBaseDeDatos();
        
        border.setTop(acciones.agregaTitulo());
        border.setCenter(acciones.creaTablaPrincipal(tabla,data));
        border.setBottom(entradaDeUsuario(border,stage));
	   
       
	    root.getChildren().add(border);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
  
   




	  /**Regresa una ista que es la que sera usada para poblar la
	   * base de datos
	   * @return Una lista con las canciones agregadas a la base de datos*/
	   
	
	public ObservableList<Cancion> poblaBaseDeDatos()  {
		  Busqueda a = new Busqueda();
		  
	        try {a.abreBaseDeDatos("musica");}
	        catch (ClassNotFoundException e){e.printStackTrace();}
	        catch (SQLException e) {e.printStackTrace();}
	        
	        try {a.busca();}
	        catch (Exception e){e.printStackTrace();}
	        return a.resultadoDeBusqueda;
	}






	  /**Agrega a la vista grafica los campos con los que trabajara el usuario
	   * @param border que se agregara
	   * @param stage  con el que se trabajara
	  */

	 public Node entradaDeUsuario(BorderPane border, Stage stage) {
		
	      GridPane grid = new GridPane();
		   grid.setAlignment(Pos.BOTTOM_LEFT);
		   grid.setHgap(4);
		   grid.setVgap(10);
		   @SuppressWarnings("unused")
		   
		   Scene scene = new Scene(grid, 400, 275);
		   TextField[] busquedas=creaCamposDeLlenados(grid);
				   
	        Button buscar =acciones.creaBoton("Buscar",grid,1,2);
	        buscar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e){busca(busquedas);}});
	        
	        Button borrar =acciones.creaBoton("Borrar",grid,2,2);
	        borrar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {borra(busquedas);}});
	        
	        Button agrega=acciones.creaBoton("Agrega", grid, 3, 2); 		
	        agrega.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {agrega(busquedas);}});
		    
	        return grid;
	}
	 
	 
	 
	  /**Agrega a la ventana los campos de llenado
	   * @param grid en donde se agregara el campo de llenado*/

	 public TextField[] creaCamposDeLlenados(GridPane grid) {
		 TextField [] busquedas= new TextField[5];
        busquedas[0]=creaCampoDeLlenado(grid,320,1,1, "Nombre De Cancion");
        busquedas[1]=creaCampoDeLlenado(grid,180,2,1,"Autor");
        busquedas[2]=creaCampoDeLlenado(grid,180,3,1,"Genero");
        busquedas[3]=creaCampoDeLlenado(grid,50,4,1,"Anio");
        busquedas[4]=creaCampoDeLlenado(grid,180,5,1,"Album");
		return busquedas;
     }



	/**Establece los valores que va a tener la cancion a agregar
	   * @param busqueda son los textfield del que se sacaran los atributos de la cancion de agregar*/
	
	public void agrega(TextField[] busquedas) {
		Cancion cancion = null;
		try {cancion = new Cancion("",busquedas[0].getText(), busquedas[1].getText(), busquedas[4].getText(), busquedas[3].getText(), busquedas[2].getText());
		} catch (Exception e) {e.printStackTrace();}
		
		data.add(cancion);
		AgregaCanciones a = null;
		
		try {a = new AgregaCanciones();
		} catch (ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
	
		try {a.agregaCancion(cancion);
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	  /**Establece los valores de los elementos a buscar de la base datos 
	   * @param t son los textfield del que se sacaran los filtros de la informacion */
	
	public void busca(TextField [] t) {
		   Busqueda a = new Busqueda();
           
			try {a.abreBaseDeDatos("musica");}
			catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
			
			if(!t[0].getText().equals("")){
			a.busquedas[0]=t[0].getText();
			}
			if(!t[1].getText().equals("")){
			a.busquedas[1]=t[1].getText();
			}
			if(!t[2].getText().equals("")){
			a.busquedas[4]=t[2].getText();
			}
			if(!t[3].getText().equals("")){
			a.busquedas[3]=t[3].getText();
			}
			if(!t[4].getText().equals("")){
			a.busquedas[2]=t[4].getText();
			}
			try {a.busca();}
			catch (Exception e){e.printStackTrace();}
			
			data=a.resultadoDeBusqueda;
		
			 tabla.setItems(data);
		
		
		
		
	}
	 
	  /**Establece los valores de los elementos a borrar de la base datos 
	   * @param t son los textfield del que se sacaran los filtros de la informacion */
	
	public void borra(TextField [] t)  {
		  Borrador a = new Borrador();
        
			try {a.abreBaseDeDatos("musica");}
			catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
			
			if(!t[0].getText().equals("")){
			a.aBorrar[0]=t[0].getText();
			}
			if(!t[1].getText().equals("")){
			a.aBorrar[1]=t[1].getText();
			}
			if(!t[2].getText().equals("")){
			a.aBorrar[4]=t[2].getText();
			}
			if(!t[3].getText().equals("")){
			a.aBorrar[3]=t[3].getText();
			}
			if(!t[4].getText().equals("")){
			a.aBorrar[2]=t[4].getText();
			}
			try {a.borra();}
			catch (Exception e) {e.printStackTrace();}
			
			  Busqueda busqueda = new Busqueda();
		        try {busqueda.abreBaseDeDatos("musica");}
		        catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
		        
		        try {busqueda.busca();
				} catch (Exception e) {e.printStackTrace();}
		        
		        data=busqueda.resultadoDeBusqueda;
		
			 tabla.setItems(data);	
	}


	   /**Regresa un campoDeLlenado con la informacion de los parametros
	   * @param grid en donde se agregara el campoDeLlenado
	   * @param i longitud del campo de llenado
	   * @param j posicion en x del campo de llenado en el grid
	   * @param k pocision en y del campo de llenado en el grid
	   * @param string backround del campo de llenado
	   * @return Textfield el campo de llenado*/

		private TextField creaCampoDeLlenado(GridPane grid, int i, int j, int k, String string) {
	 		
			TextField agrega=new TextField();
			agrega.setMaxWidth(i);
			agrega.setMinWidth(i);
			agrega.setPromptText(string);
			grid.add(agrega, j, k);
			return agrega;
		}


	public static void main(String[] args) {
        launch(args);
    }




}