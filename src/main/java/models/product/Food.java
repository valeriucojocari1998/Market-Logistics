package models.product;

import enums.ProductStatus;
import enums.ProductType;

import java.util.Date;

public class Food extends Product{
    private Date expirationDate;
    public Food(Integer price, ProductStatus status, String name, String description, Date expirationDate) {
        super(ProductType.Food, price, status, name, description);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        String typeValue = "Type: " + type;
        String priceValue = "Price: " + price;
        String statusValue = "Status: " + status;
        String nameValue = "Name: " + name;
        String descriptionValue = "Description: " + description;
        String expirationValue = "Expiration Date: " + expirationDate.toString();
        return String.join(System.lineSeparator(), "Product Object", typeValue, priceValue, statusValue, nameValue, descriptionValue, expirationValue);
    }
}
