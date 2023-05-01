package org.example;

import org.example.annotation.Command;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private Object controller;
    private Map<String, Method> methodMapping = new HashMap<>();

    public CommandDispatcher(Object controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller should be not null");
        }
        this.controller = controller;

        var clazz = controller.getClass();
        for (var method : ReflectionUtils.getAllMethods(clazz,
                m -> m.isAnnotationPresent(Command.class))) {
            method.setAccessible(true);
            methodMapping.put(method.getAnnotation(Command.class).value(), method);
        }
    }

    public void invokeMethodByCommand(String command){
        var method = methodMapping.get(command);
        if(method != null){
            try {
                method.invoke(controller);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
