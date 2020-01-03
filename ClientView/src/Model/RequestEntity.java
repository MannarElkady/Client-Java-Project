package Model;

/**
 * @author Ibrahim
 */
public class RequestEntity<T> {

    private String className;
    private String operation;    
    private T entity;

    public RequestEntity() {
    }

    public RequestEntity(String className,String operation, T entity) {
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

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
