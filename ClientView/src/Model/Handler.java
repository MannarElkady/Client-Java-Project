/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Handler {

    public static void handle(String str) {

        RequestEntity response = GsonParser.parseFromJson(str);
        ArrayList test = response.getEntity();
        RequestEntity returnValue = (RequestEntity) ReflectionClass.getObject(response.getClassName(), response.getOperation(), response.getEntity());      

    }
}
