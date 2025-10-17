package org.example;

/**
 * Интерфейс, представляющий некоторое действие.
 * <p>
 * Используется в системе Dependency Injection для демонстрации внедрения зависимостей.
 * Различные реализации этого интерфейса ({@link SomeImpl}, {@link OtherImpl})
 * предоставляют различное поведение при вызове метода {@link #doSomething()}.
 * </p>
 *
 * <p><b>Реализации:</b></p>
 * <ul>
 *   <li>{@link SomeImpl} - выводит "A"</li>
 *   <li>{@link OtherImpl} - выводит "B"</li>
 * </ul>
 *
 * <p><b>Использование в конфигурации:</b></p>
 * <pre>
 * org.example.SomeInterface=org.example.SomeImpl
 * </pre>
 *
 * @see SomeImpl
 * @see OtherImpl
 * @see SomeBean
 * @see AutoInjectable
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
public interface SomeInterface
{
    /**
     * Выполняет действие, специфичное для конкретной реализации.
     * <p>
     * В контексте лабораторной работы метод используется для демонстрации
     * работы Dependency Injection. Разные реализации выводят различные символы:
     * </p>
     * <ul>
     *   <li>{@link SomeImpl#doSomething()} - выводит "A"</li>
     *   <li>{@link OtherImpl#doSomething()} - выводит "B"</li>
     * </ul>
     */
    void doSomething();
}
