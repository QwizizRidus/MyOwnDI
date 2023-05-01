package org.example;

import org.example.annotation.MyBenchmark;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkConfigurator implements ProxyConfigurator{

    @Override
    public <T> T configure(T t) {
        var type = t.getClass();
        boolean isProxyNeeded = type.isAnnotationPresent(MyBenchmark.class)
                || !ReflectionUtils.getAllMethods(type,
                method -> method.isAnnotationPresent(MyBenchmark.class)).isEmpty();
        if(isProxyNeeded){
            return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(),
                    (proxy, method, args) ->{
                        Method classMethod = type.getMethod(method.getName()
                                ,method.getParameterTypes());
                        return invoke(t, type, method, args, classMethod);
                    });
        }
        return t;
    }

    private Object invoke(Object t, Class<?> type, Method method,
                         Object[] args, Method classMethod) throws InvocationTargetException, IllegalAccessException {
        if (type.isAnnotationPresent(MyBenchmark.class)
                || classMethod.isAnnotationPresent(MyBenchmark.class)) {
            System.out.printf("[[[BENCHMARK method %s%n", method.getName());
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.printf("Time: %dns]]]%n", end - start);
            return retVal;
        }
        return method.invoke(t, args);
    }
}
