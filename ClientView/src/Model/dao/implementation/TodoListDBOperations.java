
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.SocketConnection;
import Model.entities.TodoEntity;

/**
 *
 * @author AhmedIbrahem
 */
import Model.entities.AssignFriendTodoEntity;
import java.util.ArrayList;

/**
 *
 * @author Ibrahim
 */
public class TodoListDBOperations {
    
    public static void assignTodoRequest(ArrayList<AssignFriendTodoEntity> assignedFriendList){
        RequestEntity<AssignFriendTodoEntity> request = new RequestEntity("TodoListDBOperations", "assignTodo", assignedFriendList);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request)); 
    }
    public void assignTodoResponse(ArrayList<Object> value){
        if(value != null){
            int data =(int) value.get(0);
            if(data > 0) 
                System.out.println("user assigned");
            else
                System.out.println("user not assigned");
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
            
            System.out.println("Todo Added  successfully");
        } else {
            System.out.println("Todo not added successfully");
        }

    }
     
    public static void getAllItems(TodoEntity todo){
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todo);
        RequestEntity<TodoEntity> addRequest = new RequestEntity("TodoListDBOperations", "getAllItems", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }
    
    public void getAllItemsResonse(ArrayList<Object> items){
        if(items.size() == 0){
            System.out.println("No Items");
        }else{
            System.out.println("We have items" + items.size());
        }
    }
}
