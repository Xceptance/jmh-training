package org.xceptance.training.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Restrain the execution and explain warmup and measurement
 * 
 * @author rschwietzke
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training02
{
     @Benchmark
     public void bench()
     {
     }
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training02
      */
     
     /**
      * Results
      * 
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 5 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Throughput, ops/time
        # Benchmark: org.xceptance.training.jmh.Training02.bench
        
        # Run progress: 0.00% complete, ETA 00:00:08
        # Fork: 1 of 1
        # Warmup Iteration   1: 1641713453.126 ops/s
        # Warmup Iteration   2: 1581322414.154 ops/s
        # Warmup Iteration   3: 1549432518.891 ops/s
        Iteration   1: 1523201697.274 ops/s
        Iteration   2: 1559404970.899 ops/s
        Iteration   3: 1543778128.057 ops/s
        Iteration   4: 1597859305.474 ops/s
        Iteration   5: 1584935253.225 ops/s
        
        
        Result "org.xceptance.training.jmh.Training02.bench":
          1561835870.986 ±(99.9%) 116412105.696 ops/s [Average]
          (min, avg, max) = (1523201697.274, 1561835870.986, 1597859305.474), stdev = 30231854.167
          CI (99.9%): [1445423765.289, 1678247976.682] (assumes normal distribution)
        
        
        # Run complete. Total time: 00:00:08
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark          Mode  Cnt           Score           Error  Units
        Training02.bench  thrpt    5  1561835870.986 ± 116412105.696  ops/s
      */
}
