package org.xc.jmh.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CharHasherTest
{
    /**
     * Just ensure basic sanity
     */
    @Test
    public void test()
    {
        String [] data = {"T", "Test", "TestTest", "TestTest1", "TestTest1", "TestTestTestTestTest"};
        for (String s : data)
        {
            var h = s.hashCode();
            assertEquals(h, CharHasher.hashCodeClassic(s.toCharArray()));
            assertEquals(h, CharHasher.hashCodeNoMul(s.toCharArray()));
            assertEquals(h, CharHasher.hashCodeVectoredJDK19(s.toCharArray()));
        }
    }
}
