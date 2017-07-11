package com.teamnet.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Gabriel.Tabus on 7/10/2017.
 */
public class MyUnitTest {
    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    @Test
    public void testConcatenateNulls() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate(null,null);

        assertEquals(null,result);

        result = myUnit.concatenate(null,"two");

        assertEquals("two", result);

        result = myUnit.concatenate("one",null);

        assertEquals("one", result);
    }

    @Test
    public void testGetTheBoolean() {
        MyUnit myUnit = new MyUnit();

        //assertTrue (myUnit.getTheBoolean());

        //assertTrue((Boolean)myUnit.getTheBoolean() instanceof Boolean);

        //assertTrue(myUnit.getTheBoolean());
        assertThat(123, is(123));

        assertThat(123, not(is(2123)));

        //assertThat(true, is(myUnit.getTheBoolean()));
    }
}
