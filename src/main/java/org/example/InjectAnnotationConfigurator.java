package org.example;

import org.example.annotation.Inject;
import org.example.annotation.InjectRandomInt;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

public class InjectAnnotationConfigurator implements Configurator {
    private final ObjectFactory factory;

    public InjectAnnotationConfigurator(ObjectFactory factory) {
        this.factory = factory;
    }

    public void configure(Object obj) {
        var fields = obj.getClass().getDeclaredFields();
        for(var field:fields){
            if(field.isAnnotationPresent(Inject.class)){
                field.setAccessible(true);
                try {
                    field.set(obj,factory.getInstance(field.getType()));
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
