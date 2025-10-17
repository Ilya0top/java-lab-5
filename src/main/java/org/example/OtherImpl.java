package org.example;

/**
 * Альтернативная реализация интерфейса {@link SomeInterface}.
 * <p>
 * Выводит символ "B" при вызове метода {@link #doSomething()}.
 * Может быть использована в качестве замены {@link SomeImpl}
 * путем изменения конфигурации в файле properties.
 * </p>
 *
 * <p><b>Пример конфигурации:</b></p>
 * <pre>
 * org.example.SomeInterface=org.example.OtherImpl
 * </pre>
 *
 * @see SomeInterface
 * @see SomeBean
 * @see AutoInjectable
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
public class OtherImpl implements SomeInterface
{
    /**
     * Выполняет действие, выводящее символ "B" в консоль.
     * <p>
     * Данная реализация демонстрирует альтернативное поведение
     * по сравнению с {@link SomeImpl#doSomething()}.
     * </p>
     */
    @Override
    public void doSomething()
    {
        System.out.println("B");
    }
}
