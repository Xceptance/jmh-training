package org.xc.jmh.naive;

/**
 * You should always do a StringBuilder and not put a string together
 * manually. Also you have to size your stringbuilder. This test is garbage!
 *
 * @author rschwietzke
 *
 */
public class Example2
{
    public static void main(String[] args)
    {
        final String a = "This is a test";
        final String b = "More fun with a string";
        final long c = System.currentTimeMillis(); // e.g. 1684581249117
        var l = a.length() + b.length() + 13;

        var s1 = System.nanoTime();
        {
            var sb = new StringBuilder();
            sb.append(a);
            sb.append(b);
            sb.append(c);
            var r = sb.toString();
        }
        var e1 = System.nanoTime();

        var s2 = System.nanoTime();
        {
            var sb = new StringBuilder(l);
            sb.append(a);
            sb.append(b);
            sb.append(c);
            var r = sb.toString();
        }
        var e2 = System.nanoTime();

        var s3 = System.nanoTime();
        {
            var r = a + b + c;
        }
        var e3 = System.nanoTime();

        System.out.printf("Pure String usage  : %d ns%n", e3 - s3);
        System.out.printf("StringBuilder      : %d ns%n", e1 - s1);
        System.out.printf("Sized StringBuilder: %d ns%n", e2 - s2);
    }
}
