package Model;

import Model.entities.ItemEntity;
import Model.entities.NotificationEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Ibrahim
 */
public class GsonParser {
    public static RequestEntity parseFromJson(String request){
        Gson gson = new Gson();        
        Type requestType = null;
        System.out.println(request);
        switch(request.charAt(14)){
            case 'U':
                 requestType = new TypeToken<RequestEntity<UserEntity>>(){}.getType();
                 break;
            case 'I':
                requestType = new TypeToken<RequestEntity<ItemEntity>>(){}.getType();
                break;
            case 'N':
                requestType = new TypeToken<RequestEntity<NotificationEntity>>(){}.getType();
                break;
            case 'T':
                requestType = new TypeToken<RequestEntity<TodoEntity>>(){}.getType();
                break;
        }
        
        
        
       return gson.fromJson(request, requestType);
     
    }
    public static String parseToJson(RequestEntity req){
        
        Gson gson = new Gson();
        return gson.toJson(req);
    }
}
