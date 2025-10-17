package org.example;

/**
 * Главный класс для демонстрации работы Dependency Injection Framework
 *
 * @author ilabe
 * @version 1.0
 */
public class Main
{

    /**
     * Точка входа в приложение. Демонстрирует работу Injector.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args)
    {
        System.out.println("=== Лабораторная работа 5: Dependency Injection ===\n");

        // Показываем проблему (поля null)
        System.out.println("1. Проблема: Объект без внедрения зависимостей");
        SomeBean beanWithoutDI = new SomeBean();
        System.out.println("Состояние полей до внедрения:");
        System.out.println("   field1: " + beanWithoutDI.getField1());
        System.out.println("   field2: " + beanWithoutDI.getField2());
        System.out.print("Результат вызова foo(): ");
        beanWithoutDI.foo();

        // Показываем решение с Injector
        System.out.println("\n2. Решение: Использование Injector");
        SomeBean bean = new SomeBean();
        Injector injector = new Injector();

        System.out.println("Внедряем зависимости...");
        SomeBean injectedBean = injector.inject(bean);

        System.out.println("Состояние полей после внедрения:");
        System.out.println("   field1: " + injectedBean.getField1().getClass().getSimpleName());
        System.out.println("   field2: " + injectedBean.getField2().getClass().getSimpleName());
        System.out.print("Результат вызова foo(): ");
        injectedBean.foo();
    }
}