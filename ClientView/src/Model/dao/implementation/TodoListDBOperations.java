
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.SocketConnection;
<<<<<<< HEAD
import Model.entities.TodoEntity;
import Model.entities.UserEntity;

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
    public void assignTodoResponse(Object value){
        if(value != null){
            int data =(int) value;
            if(data > 0) 
                System.out.println("user assigned");
            else
                System.out.println("user not assigned");
        }
    }

}


    public static void addTodo(TodoEntity todo) {

        RequestEntity<TodoEntity> request = new RequestEntity("TodoListDBOperations", "addTodo", todo);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }
 
     public void addTodoResponse(Object object) {
        if (object != null) {

            System.out.println("Todo Added  successfully");
        } else {
            System.out.println("error happened  when add this toDo !");
        }

    }
}