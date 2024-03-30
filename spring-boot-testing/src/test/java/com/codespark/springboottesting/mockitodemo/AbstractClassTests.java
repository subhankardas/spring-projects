package com.codespark.springboottesting.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class AbstractClassTests {

    @Test
    public void testAbstractClass() {
        // GIVEN
        AbsTestClass absTestClass = mock(AbsTestClass.class);
        when(absTestClass.getSum(anyInt(), anyInt())).thenCallRealMethod();
        when(absTestClass.doTask(anyInt(), anyInt())).thenReturn("Test");

        // THEN
        assertEquals(3, absTestClass.getSum(1, 2));
        assertEquals("Test", absTestClass.doTask(1, 2));
    }

}

abstract class AbsTestClass {

    protected abstract String doTask(int inp1, int inp2);

    public int getSum(int inp1, int inp2) {
        return inp1 + inp2;
    }

}