package org.example;

/**
 * Реализация интерфейса {@link SomeInterface} по умолчанию.
 * <p>
 * Выводит символ "A" при вызове метода {@link #doSomething()}.
 * Является реализацией по умолчанию, указанной в конфигурационном файле.
 * </p>
 *
 * <p><b>Конфигурация по умолчанию:</b></p>
 * <pre>
 * org.example.SomeInterface=org.example.SomeImpl
 * </pre>
 *
 * @see SomeInterface
 * @see OtherImpl
 * @see SomeBean
 * @see AutoInjectable
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
public class SomeImpl implements SomeInterface
{
    /**
     * Выполняет действие, выводящее символ "A" в консоль.
     * <p>
     * Данная реализация является реализацией по умолчанию для {@link SomeInterface}.
     * При использовании в {@link SomeBean} в сочетании с {@link SODoer}
     * формирует вывод "AC".
     * </p>
     */
    @Override
    public void doSomething()
    {
        System.out.println("A");
    }
}