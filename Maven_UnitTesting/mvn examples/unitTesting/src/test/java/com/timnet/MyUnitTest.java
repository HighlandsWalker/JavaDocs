package com.timnet;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class MyUnitTest {

    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);
    }

    @Test
    public void testGetTheStringArray() {
        MyUnit myUnit = new MyUnit();

        String[] expectedArray = {"one", "two", "three"};

        String[] resultArray = myUnit.getTheStringArray();

        assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void testGetTheBoolean() {
        MyUnit myUnit = new MyUnit();

        assertTrue(myUnit.getTheBoolean());

        //assertFalse(myUnit.getTheBoolean());
        assertFalse(!myUnit.getTheBoolean());
    }

    @Test
    public void testGetTheObject() {
        MyUnit myUnit = new MyUnit();

        assertNull(myUnit.getTheObject());

        assertNotNull(myUnit.getTheObject());
    }

    @Test
    public void testGetTheSameObject() {
        MyUnit myUnit = new MyUnit();

        assertSame(myUnit.getTheSameObject(),
                myUnit.getTheSameObject());

        assertNotSame(myUnit.getTheSameObject(),
                myUnit.getTheSameObject());
    }

    @Test
    public void testWithMatchers() {
        assertThat("this string", is("this string"));
        assertThat(123, is(123));
    }

    @Test
    public void testWithChainedMatchers() {
        assertThat(123, not(is(345)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForExceptions1() {
        MyUnit myUnit = new MyUnit();

        myUnit.throwIllegalArgumentException();
    }

    @Test
    public void testForExceptions2() {
        MyUnit myUnit = new MyUnit();

        try {
            myUnit.throwIllegalArgumentException();

            fail("expected IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            //ignore, this exception is expected.
        }
    }

    @Ignore
    @Test
    public void testMath2() {
        assertThat(1 + 2, is(5));
    }

    @Ignore("some one please create a test for Math3!")
    @Test
    public void testMath3() {
        //...
    }
    
}
