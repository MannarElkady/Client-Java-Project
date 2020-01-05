package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.SocketConnection;
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
