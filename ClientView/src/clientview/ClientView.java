/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.SocketConnection;
import Model.dao.implementation.NotificationDBOperations;
import com.jfoenix.controls.JFXListView;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {

    SocketConnection object;
    
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("TodoFormXML.fxml"));
        String [] name= {"Ebrahim 1","Manar 2","yehia 3"};
        //MainXMLBase root =new MainXMLBase(name);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        //   stage.setResizable(false);
       
        
        stage.show();
    /*    ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        NotificationDBOperations.receiveNotifications(data);
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            try {
                SocketConnection.getInstance().closeSocketConnection();
            } catch (IOException ex) {
                Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.exit();
            System.exit(0);
        });
        SocketConnection.getInstance();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    public void test(){
        
          // Parent root = FXMLLoader.load(getClass().getResource("scene_2.fxml"));
        VBox root = new VBox();
        Stage stage = new Stage(StageStyle.DECORATED);
        AnchorPane pane = new AnchorPane();
        JFXListView<BorderPane> parentPane = new JFXListView<BorderPane>();
        Label text1;

        ArrayList<BorderPane> borderPanes = new ArrayList<BorderPane>();

        for (int i = 0; i < 10; i++) {
            text1 = new Label("Header");
            BorderPane border = new BorderPane();
            VBox box = new VBox();
            box.getChildren().add(text1);

            if (i == 5 || i == 3) {

                box.setAlignment(Pos.CENTER_LEFT);
                text1 = new Label("body");
                box.getChildren().add(text1);

                border.setCenter(box);
                borderPanes.add(border);
                continue;
            }
            border.setLeft(text1);

            Button b1 = new Button("accept");
            Button b2 = new Button("reject");
            b1.setId("button" + (i + 1) + "accept");
            b2.setId("button" + (i + 1) + "reject");
            b1.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            b2.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            HBox horizontal = new HBox();
            horizontal.getChildren().add(b1);
            horizontal.getChildren().add(b2);
            border.setRight(horizontal);
            borderPanes.add(border);

        }
        ObservableList<BorderPane> myObservableList = FXCollections.observableList(borderPanes);

        parentPane.setItems(myObservableList);

        Scene scene = new Scene(parentPane, 600, 600);
        Point p = MouseInfo.getPointerInfo().getLocation();
        stage.setX(p.x);
        stage.setY(p.y);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    
class MyEventHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        System.out.println(((Control) event.getSource()).getId());
    }

}
}
