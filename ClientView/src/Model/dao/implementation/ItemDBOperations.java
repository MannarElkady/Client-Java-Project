/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.entities.ItemEntity;
import java.util.ArrayList;
import Model.SocketConnection;

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
}
