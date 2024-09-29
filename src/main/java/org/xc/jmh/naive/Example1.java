package org.xc.jmh.naive;

/**
 * Just compare System.arrayCopy to a normal manual copy.
 * This is a naive benchmark and of course not correct.
 *
 * @author rschwietzke
 */
public class Example1
{
    public static void main(String[] args)
    {
        var length = 255;
        var src = new byte[length];
        var dest = new byte[length];

        var s1 = System.nanoTime();
        System.arraycopy(src, 0, dest, 0, length);
        var e1 = System.nanoTime();

        var s2 = System.nanoTime();
        for (int i = 0; i < length; i++)
        {
            dest[i] = src[i];
        }
        var e2 = System.nanoTime();

        System.out.printf("System Copy: %,d ns%n", e1 - s1);
        System.out.printf("Manual Copy: %,d ns%n", e2 - s2);
    }
}
