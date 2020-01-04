/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dell
 */
public class Handler {

    public static void handle(String str) {

        RequestEntity response = GsonParser.parseFromJson(str);
        RequestEntity returnValue = (RequestEntity) ReflectionClass.getObject(response.getClassName(), response.getOperation(), response.getEntity());      

    }
}
