/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author dell
 */
public class Handler {

    public static void handle(String str) {

        if (str != null && !str.equals("null")) {
            RequestEntity response = GsonParser.parseFromJson(str);
            ArrayList test = response.getEntity();
            RequestEntity returnValue = (RequestEntity) ReflectionClass.getObject(response.getClassName(), response.getOperation(), response.getEntity());
        }
    }
    
    public static void sendRequestToServer(RequestEntity request){
     if (!SocketConnection.getInstance().isServerClosed()) {
            try {
                SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            SocketConnection.getInstance().createConnection();
            if (SocketConnection.getInstance().isServerClosed()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("The  server is closed!");
                alert.showAndWait();
            }
            else{
                SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
            }
        }
     
    }
}
