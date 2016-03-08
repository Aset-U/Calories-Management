package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.LoggerWrapper;

/**
 * Created by Asset on 08.03.2016.
 */
public class BaseEntity {
    protected final static LoggerWrapper LOG = LoggerWrapper.get(BaseEntity.class);

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

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        if (id == null || that.id == null) {
            throw LOG.getIllegalStateException("Equals '" + this + "' and '" + that + "' with null id");
        }
        return id.equals(that.id);
    }
}
