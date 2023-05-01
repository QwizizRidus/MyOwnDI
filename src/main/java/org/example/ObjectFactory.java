package org.example;

import org.example.annotation.MyPostConstruct;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory<T> {

    private final Reflections scanner = new Reflections("org.example");
    private final List<Configurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    public ObjectFactory() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        injectConfigurators(Configurator.class,configurators);
        injectConfigurators(ProxyConfigurator.class, proxyConfigurators);
    }

    private <B> void injectConfigurators(Class<? extends B> baseClass, List<B> configurators)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var impls = scanner.getSubTypesOf(baseClass);
        for (var impl : impls) {
            Constructor<? extends B> ctor = null;
            try {
                ctor = impl.getConstructor(ObjectFactory.class);
                configurators.add(ctor.newInstance(this));
            } catch (NoSuchMethodException e) {
                configurators.add(impl.newInstance());
            }
        }
    }



    public T getInstance(Class<? extends T> clasz)
            throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        T objectInstance =  resolveImpl(clasz).getDeclaredConstructor().newInstance();

        for(var configurator: configurators){
            configurator.configure(objectInstance);
        }

        invokeInitMethods(objectInstance);

        for(var proxyConfigurator: proxyConfigurators){
            objectInstance = proxyConfigurator.configure(objectInstance);
        }

        return  objectInstance;
        ////..................
    }

    private Class<? extends T> resolveImpl(Class<? extends T> type){
        if(type.isInterface()) {
            var impls = scanner.getSubTypesOf(type);
            if (impls.size() > 1)
                throw new IllegalArgumentException("Provided interface has several implementations." +
                        " Factory can't resolve ambiguity");
            if (impls.isEmpty())
                throw new IllegalArgumentException("Provided interface has not implementations." +
                        " Factory can't create instance");
            return impls.iterator().next();
        }
        return type;
    }

    private void invokeInitMethods(Object obj) throws InvocationTargetException, IllegalAccessException {
        var methods = obj.getClass().getDeclaredMethods();
        for(var method : methods){
            if(method.isAnnotationPresent(MyPostConstruct.class)){
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }
}
