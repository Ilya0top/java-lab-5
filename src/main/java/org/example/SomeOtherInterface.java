package org.example;

/**
 * Интерфейс, представляющий дополнительное действие в системе Dependency Injection.
 * <p>
 * Используется вместе с {@link SomeInterface} для демонстрации внедрения
 * нескольких зависимостей в один класс. Реализация {@link SODoer} предоставляет
 * конкретное поведение при вызове метода {@link #doSomeOther()}.
 * </p>
 *
 * <p><b>Реализации:</b></p>
 * <ul>
 *   <li>{@link SODoer} - выводит "C"</li>
 * </ul>
 *
 * <p><b>Использование в конфигурации:</b></p>
 * <pre>
 * org.example.SomeOtherInterface=org.example.SODoer
 * </pre>
 *
 * @see SODoer
 * @see SomeInterface
 * @see SomeBean
 * @see AutoInjectable
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
public interface SomeOtherInterface
{
    /**
     * Выполняет действие, специфичное для конкретной реализации.
     * <p>
     * В контексте лабораторной работы метод используется для демонстрации
     * работы Dependency Injection. Единственная реализация выводит символ:
     * </p>
     * <ul>
     *   <li>{@link SODoer#doSomeOther()} - выводит "C"</li>
     * </ul>
     */
    void doSomeOther();
}
