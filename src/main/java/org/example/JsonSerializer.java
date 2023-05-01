package org.example;

import org.example.annotation.Publish;
import org.json.JSONObject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

public class JsonSerializer <T>{
    private Set<Field> publishedFields;

    public JsonSerializer(Class<T> serializedClass) {
        this.publishedFields = ReflectionUtils.getFields(serializedClass,
                field -> field.isAnnotationPresent(Publish.class));
        publishedFields.forEach(f -> f.setAccessible(true));
    }

    public JSONObject serialize(T o){
        JSONObject json = new JSONObject();
        for(var field:publishedFields){
            try {
                json.put(field.getName(),field.get(o));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return json;
    }
}
