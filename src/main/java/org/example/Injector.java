package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для автоматического внедрения зависимостей в объекты.
 * <p>
 * Использует механизмы рефлексии для поиска полей, помеченных аннотацией {@link AutoInjectable},
 * и инициализирует их экземплярами классов, указанных в файле конфигурации.
 * </p>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>
 * {@code
 * SomeBean bean = new SomeBean();
 * Injector injector = new Injector();
 * SomeBean injectedBean = injector.inject(bean);
 * injectedBean.foo(); // Работает без NullPointerException
 * }
 * </pre>
 *
 * @see AutoInjectable
 * @author ilabe
 * @version 1.0
 */
public class Injector
{

    private final Properties properties;

    /**
     * Загружает свойства из файла конфигурации.
     * <p>
     * Файл должен находиться в директории resources и иметь имя "properties".
     * Формат файла: полное_имя_интерфейса=полное_имя_класса_реализации
     * </p>
     *
     * @return объект Properties с загруженными настройками
     * @throws RuntimeException если файл конфигурации не найден или произошла ошибка чтения
     */
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

    /**
     * Внедряет зависимость в конкретное поле объекта.
     * <p>
     * Проверяет, что тип поля является интерфейсом, находит соответствующую реализацию
     * в конфигурации, создает экземпляр и устанавливает значение поля.
     * </p>
     *
     * @param obj объект, содержащий поле для внедрения
     * @param field поле, которое нужно инициализировать
     * @throws RuntimeException если поле не является интерфейсом или произошла ошибка внедрения
     */
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

    /**
     * Находит имя класса реализации для заданного интерфейса в конфигурации.
     *
     * @param interfaceType тип интерфейса, для которого нужно найти реализацию
     * @return полное имя класса реализации
     * @throws RuntimeException если реализация не найдена в конфигурации
     */
    private String findImplementation(Class<?> interfaceType)
    {
        String key = interfaceType.getName();
        String implementation = properties.getProperty(key);

        if (implementation == null || implementation.trim().isEmpty())
            throw new RuntimeException("No implementation found for the interface: " + key + ". Check resource/properties");

        return implementation.trim();
    }

    /**
     * Создает экземпляр класса по его имени с помощью рефлексии.
     * <p>
     * Использует конструктор по умолчанию для создания экземпляра.
     * </p>
     *
     * @param className полное имя класса для создания экземпляра
     * @return экземпляр указанного класса
     * @throws RuntimeException если не удалось создать экземпляр класса
     */
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

    /**
     * Внедряет зависимости в переданный объект.
     * <p>
     * Сканирует все поля объекта на наличие аннотации {@link AutoInjectable}
     * и инициализирует их соответствующими реализациями из конфигурации.
     * </p>
     *
     * @param obj объект, в который нужно внедрить зависимости
     * @param <T> тип объекта
     * @return тот же объект с внедренными зависимостями
     * @throws IllegalArgumentException если переданный объект равен null
     */
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

    /**
     * Создает новый экземпляр Injector и загружает конфигурацию.
     * <p>
     * Конфигурация загружается из файла "properties" в директории resources.
     * </p>
     */
    public Injector()
    {
        this.properties = loadProperties();
    }

    /**
     * Возвращает копию загруженных свойств конфигурации.
     * <p>
     * Метод предназначен в основном для тестирования.
     * </p>
     *
     * @return копия объекта Properties с загруженной конфигурацией
     */
    public Properties getProperties()
    {
        return new Properties(properties);
    }
}
