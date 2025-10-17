package org.example;

/**
 * Демонстрационный класс, использующий автоматическое внедрение зависимостей.
 * <p>
 * Содержит два поля, помеченные аннотацией {@link AutoInjectable}, которые
 * автоматически инициализируются классом {@link Injector} на основе конфигурации
 * из файла properties.
 * </p>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>
 * {@code
 * SomeBean bean = new SomeBean();
 * // Поля field1 и field2 равны null
 * bean.foo(); // field1 is null! field2 is null!
 *
 * Injector injector = new Injector();
 * SomeBean injectedBean = injector.inject(bean);
 * // Поля field1 и field2 автоматически инициализированы
 * injectedBean.foo(); // A C (зависит от конфигурации)
 * }
 * </pre>
 *
 * @see AutoInjectable
 * @see Injector
 * @see SomeInterface
 * @see SomeOtherInterface
 * @author ilabe
 * @version 1.0
 */
public class SomeBean
{
    /**
     * Поле для внедрения реализации интерфейса {@link SomeInterface}.
     * <p>
     * Автоматически инициализируется классом {@link Injector} на основе
     * конфигурации в файле properties.
     * </p>
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Поле для внедрения реализации интерфейса {@link SomeOtherInterface}.
     * <p>
     * Автоматически инициализируется классом {@link Injector} на основе
     * конфигурации в файле properties.
     * </p>
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Демонстрационный метод, использующий внедренные зависимости.
     * <p>
     * Вызывает методы внедренных объектов. Если зависимости не были внедрены,
     * выводит соответствующие сообщения об ошибках.
     * </p>
     *
     * <p><b>Пример вывода:</b></p>
     * <ul>
     *   <li>С внедренными зависимостями: "A C" (зависит от конфигурации)</li>
     *   <li>Без внедрения: "field1 is null! field2 is null!"</li>
     * </ul>
     */
    public void foo()
    {
        if (field1 != null)
            field1.doSomething();
        else
            System.out.println("field1 is null!");

        if (field2 != null)
            field2.doSomeOther();
        else
            System.out.println("field2 is null!");
    }

    /**
     * Возвращает текущую реализацию интерфейса {@link SomeInterface}.
     * <p>
     * В основном используется для тестирования и отладки.
     * </p>
     *
     * @return текущая реализация SomeInterface или null, если зависимость не внедрена
     */
    public SomeInterface getField1()
    {
        return field1;
    }

    /**
     * Возвращает текущую реализацию интерфейса {@link SomeOtherInterface}.
     * <p>
     * В основном используется для тестирования и отладки.
     * </p>
     *
     * @return текущая реализация SomeOtherInterface или null, если зависимость не внедрена
     */
    public SomeOtherInterface getField2()
    {
        return field2;
    }
}
