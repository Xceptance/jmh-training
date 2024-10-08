package org.xc.jmh.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ParseDoubleTests
{
	static class Param
	{
		String data;
		int expected;

		public Param(String data, int expected)
		{
			this.data = data;
			this.expected = expected;
		}

		double expectedAsDouble()
		{
			return expected / 10d;
		}
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseDouble(Param p)
	{
	    assertEquals(
	    		p.expectedAsDouble(),
	    		ParseDouble.parseDouble(p.data, 0, p.data.length() - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerString(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseInteger(p.data, 0, p.data.length() - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerFixed(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseIntegerFixed(p.data.getBytes(), 0,
	    				p.data.getBytes().length - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerFixed2(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseIntegerFixed2(p.data.getBytes(), 0,
	    				p.data.getBytes().length - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerFixed3(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseIntegerFixed3(p.data.getBytes(), 0,
	    				p.data.getBytes().length - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerByte(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseInteger(p.data.getBytes(), 0,
	    				p.data.getBytes().length - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseIntegerByteReverse(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseIntegerReverse(p.data.getBytes(), 0,
	    				p.data.getBytes().length - 1));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseFromByte(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseFromByte(p.data.getBytes(), 0));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseFromByteSubtractionLast(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseFromByteSubtractionLast(p.data.getBytes(), 0));
	}

	@ParameterizedTest
	@MethodSource("dataSource")
	void parseFromByteLessBranches(Param p)
	{
	    assertEquals(
	    		p.expected,
	    		ParseDouble.parseFromByteLessBranches(p.data.getBytes(), 0));
	}

	static Stream<Param> dataSource()
	{
		return Stream.of(
			      new Param("0.0", 0),
			      new Param("1.0", 10),
			      new Param("-1.0", -10),
			      new Param("99.9", 999),
			      new Param("-99.9", -999),
			      new Param("20.5", 205),
			      new Param("-21.1", -211),
			      new Param("7.9", 79),
			      new Param("-7.7", -77)
			      );
	}

}
