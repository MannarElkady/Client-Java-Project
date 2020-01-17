/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.NotificationGUIHandler;
import Model.entities.NotificationEntity;
import com.jfoenix.controls.JFXListView;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author dell
 */
public class NotificationGUI {

    public static ArrayList<NotificationEntity> notificationsListForOtherClasses = new ArrayList<>();

    public static void loadNotificationMenu(ArrayList<NotificationEntity> notificationsList) {
        if (notificationsList != null) {
            notificationsListForOtherClasses.clear();
        }
        notificationsListForOtherClasses = notificationsList;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                VBox root = new VBox();
                Stage stage = new Stage(StageStyle.UNDECORATED);
                AnchorPane pane = new AnchorPane();
                JFXListView<BorderPane> parentPane = new JFXListView<BorderPane>();
                parentPane.setId("listView");
                Label text1;

                ArrayList<BorderPane> borderPanes = new ArrayList<BorderPane>();

                if (notificationsList != null) {
                    for (int i = 0; i < notificationsList.size(); i++) {
                        text1 = new Label(notificationsList.get(i).getHeader());
                        BorderPane border = new BorderPane();
                        VBox box = new VBox();
                        box.getChildren().add(text1);

                        if (!notificationsList.get(i).getNotificationType().toLowerCase().contains("invitation")) {

                            box.setAlignment(Pos.CENTER_LEFT);
                            text1 = new Label(notificationsList.get(i).getText());
                            box.getChildren().add(text1);

                            border.setCenter(box);
                            borderPanes.add(border);

                        } else {
                            if (notificationsList.get(i).getNotificationType().contains("itemInvitation") || notificationsList.get(i).getNotificationType().contains("todoInvitation") || notificationsList.get(i).getNotificationType().contains("friendInvitation")) {
                                Button b1 = new Button("accept");
                                Button b2 = new Button("reject");
                                b1.setId("accept" + notificationsList.get(i).getNotificationType());
                                b2.setId("reject" + notificationsList.get(i).getNotificationType());
                                b1.addEventFilter(MouseEvent.MOUSE_CLICKED, new NotificationGUIHandler());
                                b2.addEventFilter(MouseEvent.MOUSE_CLICKED, new NotificationGUIHandler());
                                HBox horizontal = new HBox();
                                horizontal.getChildren().add(b1);
                                horizontal.getChildren().add(b2);
                                border.setRight(horizontal);
                                border.setLeft(text1);
                                borderPanes.add(border);
                            }

                            //}
                        }

                    }
                }
                if (notificationsList == null) {
                    text1 = new Label("test");
                    BorderPane border = new BorderPane();

                    border.getChildren().add(text1);
                    borderPanes.add(border);
                }
                ObservableList<BorderPane> myObservableList = FXCollections.observableList(borderPanes);

                parentPane.setItems(myObservableList);

                root.getChildren().add(parentPane);
                Scene scene = new Scene(root, 500, 300);
                //Point p = MouseInfo.getPointerInfo().getLocation();
                  ImageView notificationIcon =(ImageView) ClientView.mainStage.getScene().lookup("#notificationButton");
                  Image img = null;
                try {
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/notification_received.jpg"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                if(notificationIcon!=null){
                    notificationIcon.setImage(img);
                }                
                if (ClientView.mainStage.getX() - notificationIcon.getLayoutX() - 250 <= 0) {
                    stage.setX(ClientView.mainStage.getX() + notificationIcon.getLayoutX() + 450);
                }

                if (ClientView.mainStage.getX() + notificationIcon.getLayoutX() + 250 >= ClientView.mainStage.getWidth() && ClientView.mainStage.getWidth() >= 1024) {
                    stage.setX(ClientView.mainStage.getX() + notificationIcon.getLayoutX() - 450);
                } else {
                    stage.setX(ClientView.mainStage.getX() + notificationIcon.getLayoutX() - 250);
                }

                stage.setY(ClientView.mainStage.getY() + notificationIcon.getLayoutY() + 100);
                stage.setScene(scene);
                //stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (!isNowFocused) {
                        stage.hide();
                    }
                });
            }
        });

    }

    public static void receiveNotificationTray() throws FileNotFoundException {

        System.out.println("notification received");

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Image img = null;
                try {
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/notification_icon.jpg"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                Notifications notificationBuilder = Notifications.create()
                        .title("notification received")
                        .text("You received a new notification")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);

                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
                
                ImageView notificationIcon =(ImageView) ClientView.mainStage.getScene().lookup("#notificationButton");
                try {
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/notification_received.jpg"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                if(notificationIcon!=null){
                    notificationIcon.setImage(img);
                }
            }
        });

    }
}
