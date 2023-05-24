package models.user;

import java.util.Date;
import java.util.UUID;

public class Customer extends User{
    private String address;

    public Customer(String name, Integer age, String address) {
        super(name, age);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String nameValue = "Name: " + getName();
        String ageValue = "Age: " + getAge();
        String addressValue = "Address: " + getAddress();

        return String.join(System.lineSeparator(), "Customer Object", nameValue, ageValue, addressValue);
    }
}
