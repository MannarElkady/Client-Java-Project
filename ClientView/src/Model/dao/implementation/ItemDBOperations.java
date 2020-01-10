/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.MainFormHandler;
import Model.RequestEntity;
import Model.entities.ItemEntity;
import java.util.ArrayList;
import Model.SocketConnection;
import Model.entities.AssignFriendTodoEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import clientview.ClientView;
import clientview.TodoFormXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }
       
    public void addItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //to connect by Controller of ui
            System.out.println("Item Added  successfully");
        } else {
            System.out.println("Item not added successfully");
        }
    }

    public static void updateItem(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "updateItem", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(updateRequest));
    }

    public void updateItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //to connect by Controller of ui
            
            System.out.println("Item Updated  successfully");
        } else {
            System.out.println("Item not updated successfully");
        }
    }
    
    public static void assignItem(ArrayList<AssignFriendTodoEntity> assignedFriendList){
        RequestEntity<AssignFriendTodoEntity> request = new RequestEntity("ItemDBOperations", "assignItem", assignedFriendList);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request)); 
    }
    
    public void assignItemResponse(ArrayList<Object>isAssigned){
        if (isAssigned != null) {
            //to connect by Controller of ui
            System.out.println("Item assigned  successfully");
        } else {
            System.out.println("Item not assigned successfully");
        }
    }
    
    public static void deleteItem(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "deleteItem", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(updateRequest));
    }

    public void deleteItemResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            //to connect by Controller of ui
            
            System.out.println("Item deleted  successfully");
        } else {
            System.out.println("Item not deleted successfully");
        }
    }
    
     public static void getItemCollaborators(ItemEntity itemToUpdate) {
        ArrayList<ItemEntity> list = new ArrayList<>();
        list.add(itemToUpdate);
        RequestEntity<ItemEntity> updateRequest = new RequestEntity("ItemDBOperations", "getItemCollaborators", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(updateRequest));
    }
           public void getItemCollaboratorsResonse(ArrayList<UserEntity> collabotarors) {
           //clear
         // TodoFormXMLController.clearTest();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("no user in this item");
        } else {
            System.out.println("c"+collabotarors.size());
            for(int i=0;i<collabotarors.size();i++){
                System.out.println("ddd"+collabotarors.get(i).getUsername());
            }
            //TodoFormXMLController.setCollaboratorList(collabotarors);
        }
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/clientview/TodoFormXML.fxml"));
//            Scene scene = ClientView.mainStage.getScene();
//            scene.setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainFormHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
