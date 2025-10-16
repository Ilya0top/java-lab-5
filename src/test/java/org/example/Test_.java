package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test_
{

    @Test
    void testFieldsAreNullBeforeInjection()
    {
        SomeBean bean = new SomeBean();

        assertNull(bean.getField1(), "field1 должен быть null");
        assertNull(bean.getField2(), "field2 должен быть null");
    }
}
