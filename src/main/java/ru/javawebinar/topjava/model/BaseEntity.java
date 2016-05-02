package ru.javawebinar.topjava.model;

/**
 * Created by Asset on 08.03.2016.
 */
public class BaseEntity {

    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return (this.id == null);
    }
}
