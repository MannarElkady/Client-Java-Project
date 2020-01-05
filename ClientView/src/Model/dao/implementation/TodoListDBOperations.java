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
import Model.entities.UserEntity;

/**
 *
 * @author AhmedIbrahem
 */
public class TodoListDBOperations {
    
    
    
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
