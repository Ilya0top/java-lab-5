package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для пометки полей, которые должны быть автоматически внедрены.
 * <p>
 * Поля, помеченные этой аннотацией, будут автоматически инициализированы
 * соответствующими реализациями интерфейсов, указанными в файле конфигурации.
 * Класс {@link Injector} сканирует объекты на наличие полей с этой аннотацией
 * и выполняет внедрение зависимостей.
 * </p>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>
 * {@code
 * public class SomeBean {
 *     @AutoInjectable
 *     private SomeInterface field1;
 *
 *     @AutoInjectable
 *     private SomeOtherInterface field2;
 * }
 * }
 * </pre>
 *
 * @see Injector
 * @author ilabe
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable
{

}
