package models.product;

import com.azure.cosmos.implementation.JsonSerializable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enums.ProductStatus;
import enums.ProductType;
import serializers.ProductSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonSerialize(using = ProductSerializer.class)
public class Product{
    private String id;
    protected ProductType type;
    protected Integer price;
    protected ProductStatus status;
    protected String name;
    protected String description;

    public Product(ProductType type, Integer price, ProductStatus status, String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.price = price;
        this.status = status;
        this.name = name;
        this.description = description;
    }

    public Product() {};

    public String getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setId(String id) { this.id = id; }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String typeValue = "Type: " + type;
        String priceValue = "Price: " + price;
        String statusValue = "Status: " + status;
        String nameValue = "Name: " + name;
        String descriptionValue = "Description: " + description;
        return String.join(System.lineSeparator(), "Product Object", typeValue,priceValue, statusValue, nameValue, descriptionValue);
    }
}
