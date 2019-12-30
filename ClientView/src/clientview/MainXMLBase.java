package clientview;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainXMLBase extends BorderPane {
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final ColumnConstraints columnConstraints3;
    protected final RowConstraints rowConstraints;
    protected final JFXButton homeBtn;
    protected final JFXButton addFriendBtn;
    protected final ImageView appLogo;
    protected final JFXButton addTodo;
    protected final ImageView userImg;
  //  protected final FlowPane flowPane;
    protected final JFXMasonryPane jFXMasonryPane;

    public MainXMLBase(String [] todoNames) {
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        columnConstraints3 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        homeBtn = new JFXButton();
        addFriendBtn = new JFXButton();
        appLogo = new ImageView();
        addTodo = new JFXButton();
        userImg = new ImageView();
     //   flowPane = new FlowPane(Orientation.HORIZONTAL);
        jFXMasonryPane = new JFXMasonryPane();
      //  flowPane.setStyle("-fx-background-color: #1B9CE5");
        gridPane.setStyle("-fx-background-color: #FBEEC1");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(100.0);

        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setHalignment(homeBtn, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(homeBtn, javafx.geometry.VPos.CENTER);
  //      gridPane.setFitToWidth(true);
        ImageView homeImg= null;
        try {
           homeImg= new ImageView(new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal\\home.jpg")));
           homeImg.setFitHeight(30.0);
           homeImg.setFitWidth(50.0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        homeBtn.setGraphic(homeImg);

        GridPane.setColumnIndex(addFriendBtn, 1);
        GridPane.setHalignment(addFriendBtn, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(addFriendBtn, javafx.geometry.VPos.CENTER);
        ImageView imgAddFriend=null;
        try {
           imgAddFriend= new ImageView(new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal\\addFriend.png")));
           imgAddFriend.setFitHeight(30.0);
           imgAddFriend.setFitWidth(50.0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        addFriendBtn.setGraphic(imgAddFriend);

        GridPane.setColumnIndex(appLogo, 2);
        GridPane.setHalignment(appLogo, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(appLogo, javafx.geometry.VPos.CENTER);
        appLogo.setFitHeight(30.0);
        appLogo.setFitWidth(50.0);
        appLogo.setPickOnBounds(true);
        appLogo.setPreserveRatio(true);
        try {
            appLogo.setImage(new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal\\appLogo.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        GridPane.setColumnIndex(addTodo, 3);
        GridPane.setHalignment(addTodo, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(addTodo, javafx.geometry.VPos.CENTER);
        ImageView imgAdd=null;
        try {
           imgAdd= new ImageView(new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal\\add.png")));
           imgAdd.setFitHeight(30.0);
           imgAdd.setFitWidth(50.0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        addTodo.setGraphic(imgAdd);

        GridPane.setColumnIndex(userImg, 4);
        GridPane.setHalignment(userImg, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(userImg, javafx.geometry.VPos.CENTER);
        userImg.setFitHeight(30.0);
        userImg.setFitWidth(50.0);
        userImg.setPickOnBounds(true);
        userImg.setPreserveRatio(true);
        try {
            userImg.setImage(new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal\\m.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTop(gridPane);

       // BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
       BorderPane.setAlignment(jFXMasonryPane, javafx.geometry.Pos.CENTER);
        //flowPane.setPrefHeight(200.0);
      //  flowPane.setPrefWidth(200.0);
        jFXMasonryPane.setPrefHeight(200.0);
        jFXMasonryPane.setPrefWidth(200.0);
      //  setCenter(flowPane);
        setCenter(jFXMasonryPane);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getColumnConstraints().add(columnConstraints2);
        gridPane.getColumnConstraints().add(columnConstraints3);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(homeBtn);
        gridPane.getChildren().add(addFriendBtn);
        gridPane.getChildren().add(appLogo);
        gridPane.getChildren().add(addTodo);
        gridPane.getChildren().add(userImg);
 //       flowPane.getChildren().add(jFXMasonryPane);
        Image img=null;
        ImageView imgView=null;
       // FlowPane temp = null;
        for(int i=0;i<todoNames.length;i++){
            try {
            
            img= new Image(new FileInputStream("F:\\ITI life\\Java\\projectJava\\ProjectJavaRegisterReal\\src\\projectjavaregisterreal/todoIcon.png"));
            imgView=new ImageView(img);
            imgView.setFitHeight(50.0);
            imgView.setFitWidth(50.0);
            
            //temp=new FlowPane(Orientation.VERTICAL, 20.0, 20.0, imgView);
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
            }
                Label todoName = new Label(todoNames[i]);
                todoName.setGraphic(imgView);
                todoName.paddingProperty();
                todoName.setPadding(new Insets(15));
                
                jFXMasonryPane.getChildren().add(todoName);
               // temp.getChildren().add(todoName);
          //      listViewTodos.getItems().add(todoName);  
        }
    }
}
