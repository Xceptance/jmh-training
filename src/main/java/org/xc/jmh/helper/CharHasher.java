package org.xc.jmh.helper;

public class CharHasher
{

	public static int hashCodeClassic(char[] src)
	{
		final int last = src.length;

		int h = 0;
		for (int i = 0; i < last; i++)
		{
			h = 31 * h + src[i];
		}

		return h;
	}

	public static int hashCodeNoMul(char[] src)
	{
		final int last = src.length;

		int h = 0;
		for (int i = 0; i < last; i++)
		{
			h = ((h << 5) - h) + src[i];
		}

		return h;
	}

	public static int hashCodeVectoredJDK19(char[] src)
	{
		int h = 0;
		int i = 0;
		int l, l2;
		l = l2 = src.length;
		l = l & ~(8 - 1);

		for (; i < l; i += 8)
		{
			h = -1807454463 * h +
					1742810335 * src[i+0] +
					887503681 * src[i+1] +
					28629151 * src[i+2] +
					923521 * src[i+3] +
					29791 * src[i+4] +
					961 * src[i+5] +
					31 * src[i+6] +
					1 * src[i+7];
		}

		for (; i < l2; i++)
		{
			h = 31 * h + src[i];
		}

		return h;
	}
}
