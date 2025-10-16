package org.example;

public class SomeBean
{
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo()
    {
        if (field1 != null)
            field1.doSomething();
        else
            System.out.println("field1 is null!");

        if (field2 != null)
            field2.doSomeOther();
        else
            System.out.println("field2 is null!");
    }

    public SomeInterface getField1()
    {
        return field1;
    }

    public SomeOtherInterface getField2()
    {
        return field2;
    }
}
