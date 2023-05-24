package models.product;

import enums.ClothingSize;
import enums.ProductStatus;
import enums.ProductType;

public class Clothing extends Product {
private ClothingSize size;
    public Clothing(Integer price, ProductStatus status, String name, String description, ClothingSize size) {
        super(ProductType.Clothes, price, status, name, description);
        this.size = size;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String typeValue = "Type: " + type;
        String priceValue = "Price: " + price;
        String statusValue = "Status: " + status;
        String nameValue = "Name: " + name;
        String descriptionValue = "Description: " + description;
        String sizeValue = "Size: " + size;
        return String.join(System.lineSeparator(), "Product Object", typeValue, priceValue, statusValue, nameValue, descriptionValue, sizeValue);
    }
}
