package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import models.product.Clothing;
import models.product.Food;
import models.product.Product;

import java.io.IOException;

public class ProductSerializer extends JsonSerializer<Product> {

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", product.getId());
        jsonGenerator.writeStringField("type", product.getType().toString());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeStringField("status", product.getStatus().toString());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeStringField("description", product.getDescription());

        // Serialize subclass-specific fields
        if (product instanceof Food) {
            Food food = (Food) product;
            jsonGenerator.writeFieldName("expirationDate");
            jsonGenerator.writeObject(food.getExpirationDate());
        }
        if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            jsonGenerator.writeFieldName("size");
            jsonGenerator.writeObject(clothing.getSize());
        }

        jsonGenerator.writeEndObject();
    }
}
