package org.example;

/**
 * Реализация интерфейса {@link SomeOtherInterface}.
 * <p>
 * Выводит символ "C" при вызове метода {@link #doSomeOther()}.
 * Используется для демонстрации внедрения зависимостей через
 * аннотацию {@link AutoInjectable} и класс {@link Injector}.
 * </p>
 *
 * <p><b>Конфигурация по умолчанию:</b></p>
 * <pre>
 * org.example.SomeOtherInterface=org.example.SODoer
 * </pre>
 *
 * @see SomeOtherInterface
 * @see SomeBean
 * @see AutoInjectable
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
public class SODoer implements SomeOtherInterface
{
    /**
     * Выполняет дополнительное действие, выводящее символ "C" в консоль.
     * <p>
     * Метод демонстрирует работу второй зависимости в классе {@link SomeBean}.
     * В сочетании с реализацией {@link SomeInterface} формирует итоговый вывод
     * программы (например, "AC" или "BC").
     * </p>
     */
    @Override
    public void doSomeOther()
    {
        System.out.println("C");
    }
}
