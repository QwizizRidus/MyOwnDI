package org.example;

import org.example.annotation.Inject;
import org.example.annotation.InjectRandomInt;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

public class InjectRandomIntConfigurator implements Configurator{
    @Override
    public void configure(Object obj) {
        var fields = obj.getClass().getDeclaredFields();
        for(var field:fields){
            var annotation = field.getAnnotation(InjectRandomInt.class);
            if(annotation!= null && field.getType() == int.class) {
                field.setAccessible(true);
                try {
                    field.set(obj,
                            ThreadLocalRandom.current().nextInt(annotation.min(), annotation.max() + 1));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
