package org.sonatype.mavenbook;

import junit.framework.TestCase;

public class AnotherAppTest extends TestCase {
    
    public AnotherAppTest(String testName) {
        super(testName);
    }

    public void testApp()
    {
        assertEquals("a", "a");
    }
}
