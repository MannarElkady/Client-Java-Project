/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.Handler;
import Model.RequestEntity;
import Model.entities.ItemEntity;
import java.util.ArrayList;
import Model.entities.AssignFriendTodoEntity;
import Model.entities.UserAssignedToItem;
import Model.entities.UserEntity;
import clientview.ItemCollaboratorsXMLController;
import clientview.TodoFormXMLController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author dell
 */
public class ItemDBOperations {

    public static void addItem(ItemEntity item) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(item);
        RequestEntity<ItemEntity> addRequest = new RequestEntity("ItemDBOperations", "addItem", list);
        System.out.println(GsonParser.parseToJson(addRequest));
        Handler.sendRequestToServer(addRequest);
    }

    public void addItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //to connect by Controller of ui
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Item");
                    alert.setContentText("Item Added Succsessfuly");
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Item");
                    alert.setContentText("Item  Not Added ");
                    alert.showAndWait();
                }
            });
        }
    }

    public static void updateItem(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "updateItem", list);
        Handler.sendRequestToServer(updateRequest);
    }

    public void updateItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //to connect by Controller of ui
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Item");
                    alert.setContentText("Item Updated Succsessfuly");
                    alert.showAndWait();
                }
            });

        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Item");
                    alert.setContentText("Item Not Updated ");
                    alert.showAndWait();
                }
            });
        }
    }

    public static void assignItem(ArrayList<AssignFriendTodoEntity> assignedFriendList) {
        RequestEntity<AssignFriendTodoEntity> request = new RequestEntity("ItemDBOperations", "assignItem", assignedFriendList);
        Handler.sendRequestToServer(request);
    }

    public void assignItemResponse(ArrayList<Object> isAssigned) {
        if (isAssigned != null) {
            //to connect by Controller of ui
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Assign Item to user");
                    alert.setContentText("Item Assigned Succsessfuly");
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Assign Item to user");
                    alert.setContentText("Item  Not Assigned ");
                    alert.showAndWait();
                }
            });
        }
    }

    public static void deleteItem(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "deleteItem", list);
        Handler.sendRequestToServer(updateRequest);
    }

    public void deleteItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            TodoListDBOperations.getAllItems(TodoFormXMLController.todo);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Item");
                    alert.setContentText("Item Deleted Succsessfuly");
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Item ");
                    alert.setContentText("Item Not Deleted ");
                    alert.showAndWait();
                }
            });
        }
    }

    public static void getItemCollaborators(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "getItemCollaborators", list);
        Handler.sendRequestToServer(updateRequest);
    }

    public void getItemCollaboratorsResonse(ArrayList<UserEntity> collabotarors) {
        //clear
        // TodoFormXMLController.clearTest();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("no user in this item");
            ItemCollaboratorsXMLController.setItemAssignedCollaboratorsList(collabotarors);
            /*Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nothing To show");
                    alert.setHeaderText("Empty List !");
                    alert.setContentText("No Collaborators Assigned to this Item");
                    alert.show();
                }
            });*/
        } else {
            System.out.println("\nCollaborators Size: " + collabotarors.size());
            for (int i = 0; i < collabotarors.size(); i++) {
                System.out.println("Colllaborators for this items are\n" + collabotarors.get(i).getUsername());
            }

            ItemCollaboratorsXMLController.setItemAssignedCollaboratorsList(collabotarors);
         /*   Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/clientview/ItemCollaboratorsXML.fxml"));
                        Stage stage = new Stage(StageStyle.DECORATED);
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });*/
        }
    }
    
    public static void exitFromAnITem(UserAssignedToItem user){
        ArrayList<UserAssignedToItem> list = new ArrayList<>();
        list.add(user);
        RequestEntity<UserAssignedToItem> updateRequest = new RequestEntity("ItemDBOperations", "exitCollaboratorFromItem", list);
        Handler.sendRequestToServer(updateRequest);
    }
    
    public void exitFromAnItemResponse(ArrayList<UserAssignedToItem> users){
        if(users!=null && users.size()!=0){
            System.out.println("User exit successfully");
            TodoListDBOperations.getAllItems(TodoFormXMLController.todo);
        }else{
            System.out.println("User couldn't exit from the item!");
        }
    }
}
