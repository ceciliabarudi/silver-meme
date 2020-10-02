package com.petproject.henlofren;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Animal {

    @Id
    private final UUID id = UUID.randomUUID();
    private String name;

    protected Animal() {}

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(long id) {
        //transform long to uuid?
    }

    public Object getId() {
        return null;
    }
}
