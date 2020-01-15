/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.Handler;
import Model.RequestEntity;
import Model.entities.ComponentEntity;
import java.util.ArrayList;

/**
 *
 * @author AhmedIbrahem
 */
public class ComponentDBOperations {
    public static void addComponent(ComponentEntity component) {
        ArrayList<ComponentEntity> list = new ArrayList<>();
        list.add(component);                                           
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "addComponent", list);
        System.out.println(GsonParser.parseToJson(addRequest));
        Handler.sendRequestToServer(addRequest);
    }
       
    public void addComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("Componetet Added  successfully");
        } else {
            System.out.println("Compenedntd  not added successfully");
        }
    }
      public static void updateComponent(ComponentEntity component) {
        ArrayList<ComponentEntity> list = new ArrayList<>();
        list.add(component);
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "updateComponent", list);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }
       
    public void updateComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("Item Added  successfully");
        } else {
            System.out.println("Item not added successfully");
        }
    }
      public static void deleteComponent(ComponentEntity component) {
        ArrayList<ComponentEntity> list = new ArrayList<>();
        list.add(component);
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "deleteComponent", list);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }
       
    public void deleteComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("Item Deleted   successfully");
        } else {
            System.out.println("Item not added successfully");
        }
    }
    
}
