# Dependency Injection

## Описание проекта

Реализация собственного Dependency Injection (DI) на Java с использованием рефлексии и аннотаций. Проект демонстрирует принципы внедрения зависимостей и автоматической инициализации полей.

## Архитектура проекта

### Основные компоненты

- **`@AutoInjectable`** - аннотация для пометки полей, требующих автоматического внедрения
- **`Injector`** - DI контейнер, осуществляющий внедрение зависимостей
- **`SomeInterface`**, **`SomeOtherInterface`** - интерфейсы зависимостей
- **`SomeImpl`**, **`OtherImpl`**, **`SODoer`** - реализации интерфейсов
- **`SomeBean`** - демонстрационный класс с внедряемыми полями


## Использование

### 1. Создание интерфейсов и реализаций

   ```java 
    public interface MyService 
    {
    void execute();
    }

    public class MyServiceImpl implements MyService 
    {
        public void execute() 
        {
            System.out.println("Service executed");
        }
    }
   ```

### 2.Помечаем поля для внедрения

   ```java
   public class MyCalss
    {
        @AutoInjectable
        private MyService service;
        
        public void doWork()
        {
            service.execute();
        }
    }
   ```

### 3. Настраиваем конфигурацию
   ```
   org.example.MyService=org.example.MyServiceImpl
   ```

### 4. Используем Injector

   ```java
   MyClass obj = new MyClass();
   Injector injector = new Injector();
   MyClass injectedObj = injector.inject(obj);
   injectedObj.doWork();   // Выведет: Service executed
   ```

## Особенности реализации

- Рефлексия - для анализа и модификации полей во время выполнения
- Аннотации - для маркировки полей, требующих внедрения
- Динамическая загрузка - реализации загружаются по именам из конфигурации
- Гибкость - легкая смена реализаций через конфигурационный файл
- Обработка ошибок - информативные исключения при проблемах с внедрением

## Тестирование

Проект включает комплексные тесты, проверяющие:

- Корректность внедрения зависимостей
- Соответствие типов и реализаций
- Обработку граничных случаев
- Работоспособность после внедрения
- Изоляцию зависимостей

## Структура проекта
   ```
   src/
   ├── main/
   │ ├── java/org/example/
   │ │ ├── AutoInjectable.java          # Аннотация для DI
   │ │ ├── Injector.java                # DI контейнер
   │ │ ├── SomeInterface.java           # Интерфейс 1
   │ │ ├── SomeOtherInterface.java      # Интерфейс 2
   │ │ ├── SomeImpl.java                # Реализация 1
   │ │ ├── OtherImpl.java               # Реализация 2
   │ │ ├── SODoer.java                  # Реализация 3
   │ │ ├── SomeBean.java                # Класс с зависимостями
   │ │ └── Main.java                    # Демонстрация
   │ └── resources/properties           # Конфигурация DI
   └── test/java/org/example/Test_.java # Комплексные тесты
   ```