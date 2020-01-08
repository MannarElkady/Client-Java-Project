
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.MainFormHandler;
import Model.RequestEntity;
import Model.SocketConnection;
import Model.entities.AssignFriendTodoEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import clientview.ClientView;
import clientview.TodoFormXMLController;
import java.io.IOException;

/**
 *
 * @author AhmedIbrahem
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Ibrahim
 */
public class TodoListDBOperations {

    public static void assignTodoRequest(ArrayList<AssignFriendTodoEntity> assignedFriendList) {
        RequestEntity<AssignFriendTodoEntity> request = new RequestEntity("TodoListDBOperations", "assignTodo", assignedFriendList);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }

    public void assignTodoResponse(ArrayList<Object> value) {
        if (value != null) {
            int data = (int) value.get(0);
            if (data > 0) {
                System.out.println("user assigned");
            } else {
                System.out.println("user not assigned");
            }
        }
    }

    public static void addTodo(TodoEntity todo) {

        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> request = new RequestEntity("TodoListDBOperations", "addTodo", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }

    public void addTodoResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("************" + ((TodoEntity) arrayObjects.get(0)).getId());
            System.out.println("Todo Added  successfully");
        } else {
            System.out.println("Todo not added successfully");
        }

    }

    public static void getAllItems(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getAllItems", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }

    public void getAllItemsResonse(ArrayList<Object> items) {
        TodoFormXMLController.clearItemsList();
        if (items == null || items.size() == 0) {
            System.out.println("No Items");
        } else {
            TodoFormXMLController.setItems(items);
            
        }
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
            Scene scene = ClientView.mainStage.getScene();
            //root.translateYProperty().set(scene.getHeight());
            //ClientView.mainStage.setWidth(ClientView.mainStage.getScene().getWidth());
            //ClientView.mainStage.setHeight(ClientView.mainStage.getScene().getHeight());
            scene.setRoot(root);

            /*Timeline timeLine = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeLine.getKeyFrames().add(kf);
            timeLine.play();*/
        } catch (IOException ex) {
            Logger.getLogger(MainFormHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        getTodoCollaborators(TodoFormXMLController.todo);
    }
    
      public static void getTodoCollaborators(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getTodoCollaborators", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }
       public void getToDoCollaboratorsResonse(ArrayList<UserEntity> collabotarors) {
           //clear
          TodoFormXMLController.clearTest();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("No Items");
        } else {
            System.out.println(collabotarors.get(0).getEmail());
            System.out.println("co"+collabotarors.size());
            TodoFormXMLController.setCollaboratorList(collabotarors);
        }
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
            Scene scene = ClientView.mainStage.getScene();
            //root.translateYProperty().set(scene.getHeight());
            //ClientView.mainStage.setWidth(ClientView.mainStage.getScene().getWidth());
            //ClientView.mainStage.setHeight(ClientView.mainStage.getScene().getHeight());
            scene.setRoot(root);

            /*Timeline timeLine = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeLine.getKeyFrames().add(kf);
            timeLine.play();*/
        } catch (IOException ex) {
            Logger.getLogger(MainFormHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

