package serialization;

import org.example.JsonSerializer;
import org.example.entity.Person;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SerializationTest {

    static Person person = new Person();

    @BeforeAll
    static void init(){
        person.setFirstName("Alex");
        person.setLastName("Avd");
        person.setBirthdate("2001-04-23");
    }

    @Test
    void serialize_person_to_json_naive_test(){
        JSONObject json  = new JSONObject();
        json.put("firstName",person.getFirstName());
        json.put("lastName",person.getLastName());
        json.put("birthdate",person.getBirthdate());

        System.out.println(json);
    }

    @Test
    void serialize_person_to_json_my_custom_serializer_test(){
        JSONObject json = new JsonSerializer<>(Person.class).serialize(person);

        Assertions.assertEquals(json.get("firstName"),"Alex");
        Assertions.assertEquals(json.get("lastName"),"Avd");
        Assertions.assertEquals(json.get("birthdate"),"2001-04-23");
    }
}
