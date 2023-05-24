package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import models.user.Customer;
import models.user.Manager;
import models.user.User;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", user.getId().toString());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeNumberField("age", user.getAge());

        // Serialize subclass-specific fields
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            jsonGenerator.writeFieldName("enrollmentDate");
            jsonGenerator.writeNumberField("salary", manager.getSalary());
        }
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            jsonGenerator.writeStringField("address", customer.getAddress());
        }

        jsonGenerator.writeEndObject();
    }
}
