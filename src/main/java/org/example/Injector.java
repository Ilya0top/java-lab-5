package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector
{

    private final Properties properties;

    private Properties loadProperties()
    {
        Properties props = new Properties();
        String configFile = "properties";

        try(InputStream input = getClass().getClassLoader().getResourceAsStream(configFile))
        {
            if (input == null)
                throw new RuntimeException("Configuration file not found " + configFile + ". Make sure that the file is located in src/main/resources/");

            props.load(input);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error reading the configuration file: " + configFile, e);
        }
        return props;
    }

    private void injectField(Object obj, Field field)
    {
        try
        {
            Class<?> fieldType = field.getType();

            if (!fieldType.isInterface())
                throw new RuntimeException("Field " + field.getName() + " should be an interface, but it has a type: " + fieldType);

            String implementationClassName = findImplementation(fieldType);
            Object implementation = createInstance(implementationClassName);

            field.setAccessible(true);
            field.set(obj, implementation);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Injection error in the field: " + field.getName(), e);
        }
    }

    private String findImplementation(Class<?> interfaceType)
    {
        String key = interfaceType.getName();
        String implementation = properties.getProperty(key);

        if (implementation == null || implementation.trim().isEmpty())
            throw new RuntimeException("No implementation found for the interface: " + key + ". Check resource/properties");

        return implementation.trim();
    }

    private Object createInstance(String className)
    {
        try
        {
            Class<?> clazz = Class.forName(className);
            return clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to create an instance of the class: " + className, e);
        }
    }

    public <T> T inject(T obj)
    {
        if (obj == null)
            throw new IllegalArgumentException("Inject object cannot be null");

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields)
            if (field.isAnnotationPresent(AutoInjectable.class))
                injectField(obj, field);

        return obj;
    }

    public Injector()
    {
        this.properties = loadProperties();
    }

    public Properties getProperties()
    {
        return new Properties(properties);
    }
}
