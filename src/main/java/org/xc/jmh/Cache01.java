package org.xc.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Cache01
{
    @Param({"1", "10", "1000", "10000"})
    int SIZE = 1000;

    @Param({"true", "false"})
    boolean LINEAR = false;

    final List<String> data = new ArrayList<>();
    final Random random = new Random(721L);

    String[] CITIES = {"Berlin;", "Hannover;", "Prag;", "Rio;", "Hamburg;", "Paris;", "Rotterdam;"};
    String[] TEMPERATURES = {"-99.9", "99.9", "3.4", "-12.2", "22.2", "26.8", "31.1", "11.0", "-5.6"};

    @Setup
    public void setup()
    {
    	data.clear();
        final var r = new Random(7L);

        for (int i = 0; i < SIZE; i++)
        {
        	data.add(CITIES[r.nextInt(CITIES.length)] + TEMPERATURES[r.nextInt(TEMPERATURES.length)]);
        }

        if (!LINEAR)
        {
        	Collections.shuffle(data, r);
        }
    }


    @Benchmark
    public double parse()
    {
    	var total = 0d;
    	for (int i = 0; i < data.size(); i++)
        {
    		var s = data.get(i);
    		var pos = s.indexOf(';');
    		total += Double.parseDouble(s.substring(pos + 1));
        }

        return total;
    }
}
