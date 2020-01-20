package Model;

import Model.entities.ComponentEntity;
import Model.entities.FriendsEntity;
import Model.entities.ItemEntity;
import Model.entities.NotificationEntity;
import Model.entities.TodoEntity;
import Model.entities.UserAssignedToItem;
import Model.entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * @author Ibrahim
 */
public class GsonParser {

    public static RequestEntity parseFromJson(String request) {

        Gson gson = new Gson();
        Type requestType = null;
        if (request != null && !request.equals("null")) {

            System.out.println(request);
            switch (request.charAt(14)) {
                case 'U':
                    if(request.contains("removeFriendResponse")){
                      requestType = new TypeToken<RequestEntity<FriendsEntity>>() {
                        }.getType();
                    }
                    else if (request.contains("getAllTodosResonse")) {
                        requestType = new TypeToken<RequestEntity<TodoEntity>>() {
                        }.getType();
                    } else {
                        requestType = new TypeToken<RequestEntity<UserEntity>>() {
                        }.getType();
                    }
                    break;
                case 'I':
                    if (request.contains("deleteTodoResponse")) {
                        requestType = new TypeToken<RequestEntity<Integer>>() {
                        }.getType();
                    } else if (request.contains("getItemCollaboratorsResonse")) {
                        requestType = new TypeToken<RequestEntity<UserEntity>>() {
                        }.getType();
                    }else if(request.contains("exitCollaboratorFromItem")){
                        requestType = new TypeToken<RequestEntity<UserAssignedToItem>>() {
                        }.getType();
                    }else {
                        requestType = new TypeToken<RequestEntity<ItemEntity>>() {
                        }.getType();
                    }
                    break;
                case 'N':
                    requestType = new TypeToken<RequestEntity<NotificationEntity>>() {
                    }.getType();
                    break;
                case 'T':
                    //maybe there is a problem here because of integer type.
                    if (request.contains("assignTodoResponse")) {
                        requestType = new TypeToken<RequestEntity<Integer>>() {
                        }.getType();
                    } else if (request.contains("getAllItemsResonse")) {
                        requestType = new TypeToken<RequestEntity<ItemEntity>>() {
                        }.getType();
                    } else if (request.contains("getToDoCollaboratorsResonse")) {
                        requestType = new TypeToken<RequestEntity<UserEntity>>() {
                        }.getType();
                    } else if(request.contains("removeCollaboratorResponse")){ 
                        requestType = new TypeToken<RequestEntity<UserEntity>>() {
                        }.getType();
                    }else if(request.contains("gotAllItemsCollaboratorsResponse")){
                        requestType = new TypeToken<RequestEntity<UserAssignedToItem>>() {
                        }.getType();
                    }else {
                        requestType = new TypeToken<RequestEntity<TodoEntity>>() {
                        }.getType();
                    }

                    break;
                case 'C':
                        requestType = new TypeToken<RequestEntity<ComponentEntity>>() {
                        }.getType();
                  
                    break;
                    
                    case 'F':
                        requestType = new TypeToken<RequestEntity<FriendsEntity>>() {
                        }.getType();
                  
                    break;
            }
        }

        return gson.fromJson(request, requestType);

    }

    public static String parseToJson(RequestEntity req) {

        Gson gson = new Gson();
        return gson.toJson(req);
    }
}
