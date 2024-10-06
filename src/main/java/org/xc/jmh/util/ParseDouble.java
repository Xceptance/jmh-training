package org.xc.jmh.util;

/**
 * This is a small helper class for parsing char sequences and converting them into int, long, and double. This implementation is optimized for
 * speed not functionality. It is only able to parse plain numbers with base 10, e.g. 100828171. In case of parsing problems it will fall
 * back to the JDK but will lose the speed advantage of course.
 *
 * @author René Schwietzke
 */
public final class ParseDouble
{
    private static final int DIGITOFFSET = 48;

    private static final double[] multipliers = {
        1, 1, 0.1, 0.01, 0.001, 0.000_1, 0.000_01, 0.000_001, 0.000_000_1, 0.000_000_01,
        0.000_000_001, 0.000_000_000_1, 0.000_000_000_01, 0.000_000_000_001, 0.000_000_000_000_1,
        0.000_000_000_000_01, 0.000_000_000_000_001, 0.000_000_000_000_000_1, 0.000_000_000_000_000_01};

    /**
     * Parses the chars and returns the result as double. Raises a NumberFormatException in case of an non-convertible
     * char set. Due to conversion limitations, the result might be different from Double.parseDouble aka precision.
     * We also drop negative numbers and fallback to Double.parseDouble.
     *
     * @param s
     *            the characters to parse
     * @return the converted string as double
     * @throws java.lang.NumberFormatException
     */
    public static double parseDouble(final String s, final int offset, final int end)
    {
        final int negative = s.charAt(offset) == '-' ? offset + 1 : offset;

        long value = 0;
        int decimalPos = end;

        for (int i = negative; i <= end; i++)
        {
            final int d = s.charAt(i);
            if (d == '.')
            {
                decimalPos = i;
                continue;
            }
            final int v = d - DIGITOFFSET;
            value = ((value << 3) + (value << 1));
            value += v;
        }

        // adjust the decimal places
        value = negative != offset ? -value : value;
        return value * multipliers[end - decimalPos + 1];
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseInteger(final String s, final int offset, final int end)
    {
        final int negative = s.charAt(offset) == '-' ? offset + 1 : offset;

        int value = 0;

        for (int i = negative; i <= end; i++)
        {
            final int d = s.charAt(i);
            if (d == '.')
            {
                continue;
            }
            final int v = d - DIGITOFFSET;
            value = ((value << 3) + (value << 1));
            value += v;
        }

        value = negative != offset ? -value : value;
        return value;
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseInteger(final byte[] b, final int offset, final int end)
    {
        final int negative = b[offset] == (byte)'-' ? offset + 1 : offset;

        int value = 0;

        for (int i = negative; i <= end; i++)
        {
            final byte d = b[i];
            if (d == (byte)'.')
            {
                continue;
            }
            final int v = d - DIGITOFFSET;
            value = ((value << 3) + (value << 1));
            value += v;
        }

        value = negative != offset ? -value : value;
        return value;
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseIntegerReverse(final byte[] b, final int offset, final int end)
    {
        int value = 0;

        // we know the first three pieces already 9.9
        value += (b[end] - DIGITOFFSET);
        value += (b[end - 2] - DIGITOFFSET) * 10;

        final int negative = b[offset] == (byte)'-' ? offset + 1 : offset;

        for (int i = end - 3; i >= negative; i--)
        {
            final byte d = b[i];
            final int v = (d - DIGITOFFSET) * 100;
            value += v;
        }

        value = negative != offset ? -value : value;
        return value;
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseIntegerFixed(final byte[] b, final int offset, final int end)
    {
        final int length = end - offset; // one is missing, we care for that later

    	// we know the first three pieces already 9.9
    	int p0 = b[end];
    	int p1 = b[end - 2] * 10;
    	int value = p0 + p1 - (DIGITOFFSET + DIGITOFFSET * 10);

    	// we are 9.9
        if (length == 2)
        {
        	return value;
        }

        // ok, we are either -9.9 or 99.9 or -99.9
        if (b[offset] != (byte)'-')
        {
        	// we are 99.9
        	value += b[end - 3] * 100 - DIGITOFFSET * 100;
        	return value;
        }

        // we are either -99.9 or -9.9
        if (length == 3)
        {
        	// -9.9
        	return -value;
        }

        // -99.9
    	value += b[end - 3] * 100 - DIGITOFFSET * 100;
        return -value;
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseIntegerFixed2(final byte[] b, final int offset, final int end)
    {
        final int length = end - offset; // one is missing, we care for that later

    	// we know the first three pieces already 9.9
    	int p0 = b[end];
    	int p1 = b[end - 2] * 10;
    	int value = p0 + p1 - (DIGITOFFSET + DIGITOFFSET * 10);

    	int offsetByte = b[offset];
    	if (offsetByte != '-')
        {
        	// 99.9 or 9.9
            if (length == 3)
            {
        		// we are 99.9
            	value += (offsetByte * 100) - DIGITOFFSET * 100;
            }

        	return value;
        }
        else
        {
        	// -99.9 or -9,9
            if (length == 4)
            {
        		// we are -99.9
            	value += (b[end - 3] * 100) - DIGITOFFSET * 100;
            }

            return -value;
        }
    }

    /**
     * Parses a double but ends up with an int, only because we know
     * the format of the results -99.9 to 99.9
     */
    public static int parseIntegerFixed3(final byte[] b, final int offset, final int end)
    {
        final int length = end - offset; // one is missing, we care for that later

    	// we know the first three pieces already 9.9
    	int p0 = b[end];
    	int p1 = b[end - 2] * 10;
    	int value = p0 + p1;

    	int offsetByte = b[offset];
    	if (offsetByte != '-')
        {
        	// 99.9 or 9.9
            if (length == 3)
            {
        		// we are 99.9
            	value += (offsetByte * 100);
            	value -= (DIGITOFFSET * 100) + (DIGITOFFSET * 10) + DIGITOFFSET;
            }
            else
            {
            	value -= (DIGITOFFSET * 10) + DIGITOFFSET;
            }

        	return value;
        }
        else
        {
        	// -99.9 or -9,9
            if (length == 4)
            {
        		// we are -99.9
            	value += (b[end - 3] * 100);
            	value -= (DIGITOFFSET * 100) + (DIGITOFFSET * 10) + DIGITOFFSET;
            }
            else
            {
            	value -= (DIGITOFFSET * 10) + DIGITOFFSET;
            }

            return -value;
        }
    }

    /**
     * Parse int from byte without knowning the end, because we know the structure
     */
    public static int parseFromByte(final byte[] data, final int pos)
    {
    	int i = pos;

        byte b = data[i++];
        int negative;

        // can be - or 0..9
        if (b == '-')
        {
            negative = -1;
            // read number again
            b = data[i++];
        }
        else
        {
            negative = 1;
        }

        // ok, number for sure, -9 or 9
        int value = b - DIGITOFFSET;
        b = data[i++];

        // now -99 or -9. or 99 or 9.
        if (b == '.')
        {
            // read again for the data after the .
            b = data[i];
            value *= 10;
            value += b - DIGITOFFSET;
            return negative * value;
        }
        else
        {
            // was -99 or 99
            i++;
            byte b2 = data[i];

            value *= 10;
            value += b - DIGITOFFSET;

            // we have seen the end now for certain
            // skip over .
            value *= 10;
            value += b2 - DIGITOFFSET;

            return negative * value;
        }
    }

    /**
     * Parse int from byte without knowning the end, because we know the structure
     */
    public static int parseFromByteSubtractionLast(final byte[] data, final int pos)
    {
    	int i = pos;

        // we know for the numbers that we are very fix in length,
        // so let's read forward
        // we don't check if we have enough data because we have correct
        // data and we read early enough to have always a full line in the buffer
        byte b = data[i++];
        int negative;

        // can be - or 0..9
        if (b == '-')
        {
            negative = -1;
            // read number again
            b = data[i++];
        }
        else
        {
            negative = 1;
        }

        // ok, number for sure, -9 or 9
        int value = b;
        b = data[i++];

        // now -99 or -9. or 99 or 9.
        if (b == '.')
        {
            // read again for the data after the .
            b = data[i];
            value *= 10;
            value += b;
            return negative * (value - DIGITOFFSET - DIGITOFFSET * 10);
        }
        else
        {
            // was -99 or 99
            value *= 10;
            value += b;

            // we have seen the end now for certain
            // skip over .
            i++;
            byte b2 = data[i];

            value *= 10;
            value += b2;

            return negative * (value - DIGITOFFSET - DIGITOFFSET * 10 - DIGITOFFSET * 100);
        }
    }
}