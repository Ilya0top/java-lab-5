package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Test_
{

    private Injector injector;

    @BeforeEach
    void setUp()
    {
        injector = new Injector();
    }

    @Test
    void testFieldsAreNullBeforeInjection()
    {
        SomeBean bean = new SomeBean();

        assertNull(bean.getField1(), "field1 должен быть null");
        assertNull(bean.getField2(), "field2 должен быть null");
    }

    @Test
    void testInjectorCreation()
    {
        assertNotNull(injector, "Injector должен создаваться успешно");
    }

    @Test
    void testInjectFillsAllAnnotatedFields()
    {
        SomeBean bean = new SomeBean();
        SomeBean injectedBean = injector.inject(bean);

        assertNotNull(injectedBean.getField1(), "field1 должен быть заполнен после внедрения");
        assertNotNull(injectedBean.getField2(), "field2 должен быть заполнен после внедрения");
    }

    @Test
    void testInjectedFieldsHaveCorrectTypes()
    {
        SomeBean bean = new SomeBean();
        SomeBean injectedBean = injector.inject(bean);

        assertTrue(injectedBean.getField1() instanceof SomeInterface, "field1 должен реализовывать SomeInterface");
        assertTrue(injectedBean.getField2() instanceof SomeOtherInterface, "field2 должен реализовывать SomeOtherInterface");
    }

    @Test
    void testInjectedFieldsAreCorrectImplementations()
    {
        SomeBean bean = new SomeBean();
        SomeBean injectedBean = injector.inject(bean);

        assertEquals(SomeImpl.class, injectedBean.getField1().getClass(), "field1 должен быть экземпляром SomeImpl (согласно config.properties)");
        assertEquals(SODoer.class, injectedBean.getField2().getClass(), "field2 должен быть экземпляром SODoer (согласно config.properties)");
    }

    @Test
    void testFooMethodWorksAfterInjection()
    {
        SomeBean bean = new SomeBean();
        SomeBean injectedBean = injector.inject(bean);

        assertDoesNotThrow(() -> injectedBean.foo(), "foo() не должен бросать исключений после внедрения");
    }

    @Test
    void testInjectReturnsSameInstance()
    {
        SomeBean originalBean = new SomeBean();
        SomeBean returnedBean = injector.inject(originalBean);

        assertSame(originalBean, returnedBean, "inject() должен возвращать тот же объект, что был передан");
    }

    @Test
    void testMultipleInjectCalls()
    {
        SomeBean bean1 = new SomeBean();
        SomeBean bean2 = new SomeBean();

        SomeBean injected1 = injector.inject(bean1);
        SomeBean injected2 = injector.inject(bean2);

        assertNotNull(injected1.getField1());
        assertNotNull(injected1.getField2());
        assertNotNull(injected2.getField1());
        assertNotNull(injected2.getField2());

        assertNotSame(injected1, injected2, "Объекты должны быть разными");
    }

    @Test
    void testInjectNullObject()
    {
        assertThrows(IllegalArgumentException.class, () -> injector.inject(null), "Должна быть ошибка при передаче null");
    }

    @Test
    void testAutoInjectableAnnotationExists()
    {
        Field[] fields = SomeBean.class.getDeclaredFields();

        boolean field1Annotated = false;
        boolean field2Annotated = false;

        for (Field field : fields)
        {
            if (field.getName().equals("field1") && field.isAnnotationPresent(AutoInjectable.class))
                field1Annotated = true;

            if (field.getName().equals("field2") && field.isAnnotationPresent(AutoInjectable.class))
                field2Annotated = true;
        }

        assertTrue(field1Annotated, "field1 должен иметь аннотацию @AutoInjectable");
        assertTrue(field2Annotated, "field2 должен иметь аннотацию @AutoInjectable");
    }

    @Test
    void testFooOutputWithInjection()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try
        {
            SomeBean bean = new SomeBean();
            SomeBean injectedBean = injector.inject(bean);
            injectedBean.foo();

            String output = outputStream.toString();
            assertTrue(output.contains("A"), "Вывод должен содержать 'A' от SomeImpl");
            assertTrue(output.contains("C"), "Вывод должен содержать 'C' от SODoer");

        }
        finally
        {
            System.setOut(originalOut);
        }
    }

    @Test
    void testOnlyAnnotatedFieldsAreInjected()
    {

        class TestClass
        {
            @AutoInjectable
            private SomeInterface injectedField;

            private String normalField;

            public SomeInterface getInjectedField() { return injectedField; }
            public String getNormalField() { return normalField; }
        }

        TestClass testObj = new TestClass();
        TestClass injectedObj = injector.inject(testObj);

        assertNotNull(injectedObj.getInjectedField(), "Аннотированное поле должно быть заполнено");
        assertNull(injectedObj.getNormalField(), "Неаннотированное поле должно остаться null");
    }

    /** Тест проверяет, что конфигурация загружается без ошибок */
    @Test
    void testConfigurationPropertiesAreLoaded()
    {
        assertDoesNotThrow(() -> new Injector(), "Injector должен создаваться без ошибок (конфигурация загружена)");
    }

    @Test
    void testDifferentObjectsGetDifferentInstances()
    {
        SomeBean bean1 = new SomeBean();
        SomeBean bean2 = new SomeBean();

        SomeBean injected1 = injector.inject(bean1);
        SomeBean injected2 = injector.inject(bean2);

        assertNotSame(injected1.getField1(), injected2.getField1(), "field1 разных объектов должны быть разными экземплярами");
        assertNotSame(injected1.getField2(), injected2.getField2(), "field2 разных объектов должны быть разными экземплярами");
    }
}
