package proyecto;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


/** <p>Clase que realiza operaciones de JavaFx su funcion es hacer mas legible la clase
 * ventanas y realizar funciones  de JavaFX  necesarias para la visualizacion de la 
 * base de datos.</p>*/

public class AccionesEnJavaFX {
	

	   /**Modifica el Stage con ajustes basicos de ancho altura y el texto
	    * que aparece en el borde superior de la pantalla
	    *  @param stage   el Stage a modificar
	    *  @param string  el titulo de la ventana
	    *  @param i       el ancho del stage	
	    *  @param j       la altura del stage*/
	
	public void ajustesBasicosDeStage(Stage stage, String string, int i, int j) {
  	  stage.setTitle(string);
        stage.setWidth(i);
        stage.setHeight(j);
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	  /**Modifica una tabla , crea las columnas pertinentes para representar una 
	    * cancion , y agrega la informacion de la Lista Data
	    *  @param tabla   la tabla a modificar
	    *  @param data    lista de canciones de donde se obtiene la informacion	*/
	
	public  void creaTablas(TableView<Cancion> tabla, ObservableList<Cancion> data) throws Exception {
        TableColumn nombre =creaColumna("Nombre", 320, "nombre");
        TableColumn autor=creaColumna("Autor", 180, "autor");
        TableColumn album = creaColumna("Genero", 180, "album");	
        TableColumn anio =creaColumna("Anio", 50, "anio");
        TableColumn genero=creaColumna("Album", 180, "genero");
        tabla.getColumns().addAll(nombre,autor,album,anio,genero);
      
   	 tabla.setItems(data);
        
		
	}
	
	  /** Crea una columna con los valores de los parametros
	    * 
	    *  @return una Columna 
	    *  @param string   Nombre de la columna
	    *  @param i        Ancho de la columna
	    *  @param  string2  La informacion de la columna	*/


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableColumn creaColumna(String string, int i, String string2) {
    	
	TableColumn firstNameCol =new TableColumn(string);
    firstNameCol.setMinWidth(i);
    firstNameCol.setCellValueFactory(new PropertyValueFactory<Cancion, String>(string2));
    
    
    switch(string2){
    
    case "nombre":
    firstNameCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Cancion, String>>() {
            @Override
            public void handle(CellEditEvent<Cancion, String> t) {
                ((Cancion) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setNombre(t.getNewValue());
            }
         }
    );
    
    break;
    
    case "autor":
    
    firstNameCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Cancion, String>>() {
             @Override
             public void handle(CellEditEvent<Cancion, String> t) {
               ((Cancion) t.getTableView().getItems().get( 
            		t.getTablePosition().getRow())
    	             ).setAutor(t.getNewValue());
             }
 	      }
     );
    	   
    	   break;
    	   
    case "anio":

        
    firstNameCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Cancion, String>>() {
             @Override
             public void handle(CellEditEvent<Cancion, String> t) {
                ((Cancion) t.getTableView().getItems().get(
                	t.getTablePosition().getRow())
                    ).setAnio(t.getNewValue());
               }
           }
       );
    	
    	break;
    	
    case "album":

        
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Cancion, String>>() {
                 @Override
                 public void handle(CellEditEvent<Cancion, String> t) {
                    ((Cancion) t.getTableView().getItems().get(
                    	t.getTablePosition().getRow())
                        ).setAlbum(t.getNewValue());
                   }
               }
           );
        	
        	break;
        	
   case "genero":

        
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Cancion, String>>() {
                 @Override
                 public void handle(CellEditEvent<Cancion, String> t) {
                    ((Cancion) t.getTableView().getItems().get(
                    	t.getTablePosition().getRow())
                        ).setGenero(t.getNewValue());
                   }
               }
           );
        	
        	break;
      }   
    return firstNameCol;
	}
	
	  /** Crea una columna con los valores de los parametros
	    * 
	    *  @return un Boton 
	    *  @param  string   Texto del boton
	    *  @param  grid     GridPane al que se agregara el boton
	    *  @param  i,j      Posicion en el GridPane*/

	
	public Button creaBoton(String string, GridPane grid, int i, int j) {
		Button boton=new Button(string);
		    HBox hbBtn = new HBox(10);
		    hbBtn.getChildren().add(boton);
		    grid.add(hbBtn,i,j);
			return boton;
		}



	   /** Regresa un nodo conLa Tabla Principal , que contiene la informacion
	    *  de la lista data
	    * 
	    *  @return Nodo 
	    *  @param tabla   Tabla a agregar
	    *  @param  data   Datos que contendra la tabla*/
		public Node creaTablaPrincipal(TableView<Cancion> tabla, ObservableList<Cancion> data) throws Exception {
	    	tabla.setEditable(true);
	        this.creaTablas(tabla,data);
	        HBox hb = new HBox();
	        hb.setSpacing(3);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(tabla, hb);
			return vbox;
	 
		}
		 /** Regresa un HBox con el titulo Tonio Tunes , ya con formato
		    *@return HBox nodo con el titulo TonioGebra*/
		
		public HBox agregaTitulo() {
			
		    HBox hbox = new HBox();
		    hbox.setPadding(new Insets(15, 12, 15, 12));
		    hbox.setSpacing(10);
		    
		    String family = "Helvetica";
		    double size = 20;

		    TextFlow textFlow = new TextFlow();
		   
		    Text text1 = new Text("Tonio");
		    text1.setFont(Font.font(family, FontWeight.BOLD,size));
		    text1.setFill(Color.RED);
		    Text text2 = new Text("Tunes");
		    text2.setFill(Color.ORANGE);
		    text2.setFont(Font.font(family, FontWeight.BOLD, size));
		
		    textFlow.getChildren().addAll(text1, text2);
		     
		  
		    text2.setFont(Font.font(family, FontWeight.BOLD, size));
		    hbox.getChildren().addAll(textFlow);

		    return hbox;
		}


	

	

}
