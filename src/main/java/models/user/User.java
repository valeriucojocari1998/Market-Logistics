package models.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import serializers.ProductSerializer;
import serializers.UserSerializer;

import java.util.Date;
import java.util.UUID;

@JsonSerialize(using = UserSerializer.class)
public class User {
    private String id;
    protected String name;
    protected Integer age;

    public User(String name, Integer age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public User() {}
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String nameValue = "Name: " + getName();
        String ageValue = "Age: " + getAge();

        return String.join(System.lineSeparator(), "Manager Object", nameValue, ageValue);
    }
}
