
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.Handler;
import Model.RequestEntity;
import Model.entities.AssignFriendTodoEntity;
import Model.entities.TodoCollaboratorEntity;
import Model.entities.TodoEntity;
import Model.entities.UserAssignedToItem;
import Model.entities.UserEntity;
import clientview.ClientView;
import clientview.TodoFormXMLController;
import java.io.IOException;

/**
 *
 * @author AhmedIbrahem
 */
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 *
 * @author Ibrahim
 */
public class TodoListDBOperations {

    public static void assignTodoRequest(ArrayList<AssignFriendTodoEntity> assignedFriendList) {
        RequestEntity<AssignFriendTodoEntity> request = new RequestEntity("TodoListDBOperations", "assignTodo", assignedFriendList);
        Handler.sendRequestToServer(request);
    }

    public void assignTodoResponse(ArrayList<Object> value) {
        if (value != null) {
            int data = (int) value.get(0);
            if (data > 0) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("User Assign");
                        alert.setContentText("User Assign Succsessfuly");
                        alert.showAndWait();
                    }
                });
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("User Assign");
                        alert.setContentText("User Not Assigned ");
                        alert.showAndWait();
                    }
                });
            }
        }
    }

    public static void addTodo(TodoEntity todo) {

        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> request = new RequestEntity("TodoListDBOperations", "addTodo", list);
        Handler.sendRequestToServer(request);
    }

    public void addTodoResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //     System.out.println("************" + ((TodoEntity) arrayObjects.get(0)).getId());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo information");
                    alert.setHeaderText(null);
                    alert.setContentText(((TodoEntity) arrayObjects.get(0)).getTitle() + "Todo Added Successfuly");
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo information");
                    alert.setHeaderText(null);
                    alert.setContentText("Todo  Not Added ");
                    alert.showAndWait();
                }
            });
        }

    }

    public static void getAllItems(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getAllItems", list);
        Handler.sendRequestToServer(addRequest);
    }

    public void getAllItemsResonse(ArrayList<Object> items) {
        TodoFormXMLController.clearItemsList();
        if (items == null || items.size() == 0) {
        } else {
            TodoFormXMLController.setItems(items);
        }
        getTodoCollaborators(TodoFormXMLController.todo);
        getAllItemsCollaborators(TodoFormXMLController.todo);
    }

    public static void getTodoCollaborators(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getTodoCollaborators", list);
        Handler.sendRequestToServer(addRequest);
    }

    public void getToDoCollaboratorsResonse(ArrayList<UserEntity> collabotarors) {
        //clear
        TodoFormXMLController.clearCollaboratorList();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("No Items");
        } else {
            System.out.println(collabotarors.get(0).getEmail());
            System.out.println("co" + collabotarors.size());
            TodoFormXMLController.setCollaboratorList(collabotarors);
        }

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    ClientView.mainStage.sizeToScene();
                    ClientView.mainStage.setMinWidth(785);
                    ClientView.mainStage.setMinHeight(500);
                    ClientView.mainStage.setResizable(true);
                    Parent root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
                    Scene scene = ClientView.mainStage.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void updateTodo(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> request = new RequestEntity("TodoListDBOperations", "updateTodo", list);
        Handler.sendRequestToServer(request);
    }

    public void updateTodoResponse(ArrayList<Object> todoList) {
        if (todoList == null || todoList.size() == 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo Update");
                    alert.setContentText("Todo   Not Updated ");
                    alert.showAndWait();
                }
            });
        } else {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo Update");
                    alert.setContentText("Todo  Updated Successfully");
                    alert.showAndWait();
                    try {
                        TodoFormXMLController.setToDoData((TodoEntity) todoList.get(0));
                        Parent root;
                        ClientView.mainStage.sizeToScene();
                        ClientView.mainStage.setMinWidth(785);
                        ClientView.mainStage.setMinHeight(500);
                        ClientView.mainStage.setResizable(true);
                        root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
                        Scene scene = ClientView.mainStage.getScene();
                        scene.setRoot(root);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
    }

    public static void deleteTodo(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> request = new RequestEntity("TodoListDBOperations", "deleteTodo", list);
        Handler.sendRequestToServer(request);
    }

    public void deleteTodoResponse(ArrayList<Object> todoList) {
        if (todoList == null || todoList.size() == 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo Deleted");
                    alert.setContentText("Todo  Deleted Successfully");
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Todo Deleted");
                    alert.setContentText("Todo Not  Deleted ");
                    alert.showAndWait();
                }
            });
            UserDBOperations.getAllTodos(ClientView.currentUser);

        }
    }

    public static void removeCollaborator(TodoCollaboratorEntity todoCollaborator) {
        ArrayList<TodoCollaboratorEntity> list = new ArrayList<>();
        list.add(todoCollaborator);
        RequestEntity<TodoCollaboratorEntity> addRequest = new RequestEntity("TodoListDBOperations", "removeTodoCollaborator", list);
        Handler.sendRequestToServer(addRequest);
    }

    public void removeCollaboratorResponse(ArrayList<Object> value) {
        if (value == null || value.size() == 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Collaborator Remove");
                    alert.setContentText("Collaborator  Not Removed");
                    alert.showAndWait();
                }
            });
        } else {

//            TodoFormXMLController.todo = null;
            if (TodoFormXMLController.todo.getCreatorId() == ((UserEntity) value.get(0)).getId() && ClientView.currentUser.getId() == ((UserEntity) value.get(0)).getId() && ClientView.whichScreen.equals("" + TodoFormXMLController.todo.getId())) {
                getAllItems(TodoFormXMLController.todo);
            } else {
                TodoFormXMLController.todo = null;
            }            
        }
    }
    
    public static void getAllItemsCollaborators(TodoEntity todo) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getAllItemsCollaborators", list);
        Handler.sendRequestToServer(addRequest);
    }

    public void gotAllItemsCollaboratorsResponse(ArrayList<UserAssignedToItem> collabotarors) {
        //clear
        TodoFormXMLController.clearCollaboratorsList();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("No Collaborators Assigned to items");
        } else {
            System.out.println("retrieved collaborators successfully!");
            TodoFormXMLController.setItemsCollaborators(collabotarors);
        }
        Platform.runLater(()->{
                try {
                    ClientView.mainStage.sizeToScene();
                        ClientView.mainStage.setMinHeight(500);
                        ClientView.mainStage.setMinWidth(700);
                        ClientView.mainStage.setResizable(true);
                    Parent root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
                    Scene scene = ClientView.mainStage.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }
}
