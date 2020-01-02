/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DELL
 */
public class RequestCreator {
    private String operation;
    private String className;
    private Object entity;
    
    
    public RequestCreator(){
        this.operation = "";
        this.className = "";
        this.entity = null;
    }
    
    public RequestCreator(String operation, String className, Object entity) {
        this.operation = operation;
        this.className = className;
        this.entity = entity;
    }

    
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
