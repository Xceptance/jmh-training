package org.xceptance.training.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Get us better readable results
 * 
 * @author rschwietzke
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training03
{
     @Benchmark
     @BenchmarkMode(Mode.AverageTime)
     @OutputTimeUnit(TimeUnit.MICROSECONDS)
     public void benchAvg() throws InterruptedException
     {
         TimeUnit.MILLISECONDS.sleep(100);
     }

     @Benchmark
     @BenchmarkMode(Mode.SampleTime)
     @OutputTimeUnit(TimeUnit.MILLISECONDS)
     public void benchSample() throws InterruptedException
     {
         TimeUnit.MILLISECONDS.sleep(100);
     }

     @Benchmark
     @BenchmarkMode(Mode.Throughput)
     @OutputTimeUnit(TimeUnit.SECONDS)
     public void benchThroughput() throws InterruptedException
     {
         TimeUnit.MILLISECONDS.sleep(100);
     }
     
     @Benchmark
     @BenchmarkMode(Mode.SingleShotTime)
     @OutputTimeUnit(TimeUnit.MILLISECONDS)
     public void benchSingle() throws InterruptedException
     {
         TimeUnit.MILLISECONDS.sleep(100);
     }

     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training03
      */
     
     /**
      * Results
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 5 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Throughput, ops/time
        # Benchmark: org.xceptance.training.jmh.Training03.benchThroughput
        
        # Run progress: 0.00% complete, ETA 00:00:24
        # Fork: 1 of 1
        # Warmup Iteration   1: 9.982 ops/s
        # Warmup Iteration   2: 9.983 ops/s
        # Warmup Iteration   3: 9.982 ops/s
        Iteration   1: 9.982 ops/s
        Iteration   2: 9.983 ops/s
        Iteration   3: 9.983 ops/s
        Iteration   4: 9.981 ops/s
        Iteration   5: 9.981 ops/s
        
        
        Result "org.xceptance.training.jmh.Training03.benchThroughput":
          9.982 ±(99.9%) 0.003 ops/s [Average]
          (min, avg, max) = (9.981, 9.982, 9.983), stdev = 0.001
          CI (99.9%): [9.979, 9.985] (assumes normal distribution)
        
        
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 5 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Average time, time/op
        # Benchmark: org.xceptance.training.jmh.Training03.benchAvg
        
        # Run progress: 33.32% complete, ETA 00:00:16
        # Fork: 1 of 1
        # Warmup Iteration   1: 100168.856 us/op
        # Warmup Iteration   2: 100161.494 us/op
        # Warmup Iteration   3: 100179.433 us/op
        Iteration   1: 100154.232 us/op
        Iteration   2: 100193.307 us/op
        Iteration   3: 100200.720 us/op
        Iteration   4: 100187.764 us/op
        Iteration   5: 100176.099 us/op
        
        
        Result "org.xceptance.training.jmh.Training03.benchAvg":
          100182.424 ±(99.9%) 69.858 us/op [Average]
          (min, avg, max) = (100154.232, 100182.424, 100200.720), stdev = 18.142
          CI (99.9%): [100112.567, 100252.282] (assumes normal distribution)
        
        
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 5 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Sampling time
        # Benchmark: org.xceptance.training.jmh.Training03.benchSample
        
        # Run progress: 66.64% complete, ETA 00:00:08
        # Fork: 1 of 1
        # Warmup Iteration   1: 100.113 ±(99.9%) 0.084 ms/op
        # Warmup Iteration   2: 100.126 ±(99.9%) 0.063 ms/op
        # Warmup Iteration   3: 100.113 ±(99.9%) 0.084 ms/op
        Iteration   1: 100.113 ±(99.9%) 0.084 ms/op
                         benchSample·p0.00:   100.008 ms/op
                         benchSample·p0.50:   100.139 ms/op
                         benchSample·p0.90:   100.139 ms/op
                         benchSample·p0.95:   100.139 ms/op
                         benchSample·p0.99:   100.139 ms/op
                         benchSample·p0.999:  100.139 ms/op
                         benchSample·p0.9999: 100.139 ms/op
                         benchSample·p1.00:   100.139 ms/op
        
        Iteration   2: 100.100 ±(99.9%) 0.096 ms/op
                         benchSample·p0.00:   100.008 ms/op
                         benchSample·p0.50:   100.139 ms/op
                         benchSample·p0.90:   100.139 ms/op
                         benchSample·p0.95:   100.139 ms/op
                         benchSample·p0.99:   100.139 ms/op
                         benchSample·p0.999:  100.139 ms/op
                         benchSample·p0.9999: 100.139 ms/op
                         benchSample·p1.00:   100.139 ms/op
        
        Iteration   3: 100.126 ±(99.9%) 0.063 ms/op
                         benchSample·p0.00:   100.008 ms/op
                         benchSample·p0.50:   100.139 ms/op
                         benchSample·p0.90:   100.139 ms/op
                         benchSample·p0.95:   100.139 ms/op
                         benchSample·p0.99:   100.139 ms/op
                         benchSample·p0.999:  100.139 ms/op
                         benchSample·p0.9999: 100.139 ms/op
                         benchSample·p1.00:   100.139 ms/op
        
        Iteration   4: 100.100 ±(99.9%) 0.096 ms/op
                         benchSample·p0.00:   100.008 ms/op
                         benchSample·p0.50:   100.139 ms/op
                         benchSample·p0.90:   100.139 ms/op
                         benchSample·p0.95:   100.139 ms/op
                         benchSample·p0.99:   100.139 ms/op
                         benchSample·p0.999:  100.139 ms/op
                         benchSample·p0.9999: 100.139 ms/op
                         benchSample·p1.00:   100.139 ms/op
        
        Iteration   5: 100.113 ±(99.9%) 0.084 ms/op
                         benchSample·p0.00:   100.008 ms/op
                         benchSample·p0.50:   100.139 ms/op
                         benchSample·p0.90:   100.139 ms/op
                         benchSample·p0.95:   100.139 ms/op
                         benchSample·p0.99:   100.139 ms/op
                         benchSample·p0.999:  100.139 ms/op
                         benchSample·p0.9999: 100.139 ms/op
                         benchSample·p1.00:   100.139 ms/op
        
        
        
        Result "org.xceptance.training.jmh.Training03.benchSample":
          N = 50
          mean =    100.110 ±(99.9%) 0.027 ms/op
        
          Histogram, ms/op:
            [100.000, 100.013) = 11 
            [100.013, 100.025) = 0 
            [100.025, 100.038) = 0 
            [100.038, 100.050) = 0 
            [100.050, 100.063) = 0 
            [100.063, 100.075) = 0 
            [100.075, 100.088) = 0 
            [100.088, 100.100) = 0 
            [100.100, 100.113) = 0 
            [100.113, 100.125) = 0 
            [100.125, 100.138) = 0 
            [100.138, 100.150) = 39 
            [100.150, 100.163) = 0 
            [100.163, 100.175) = 0 
            [100.175, 100.188) = 0 
            [100.188, 100.200) = 0 
        
          Percentiles, ms/op:
              p(0.0000) =    100.008 ms/op
             p(50.0000) =    100.139 ms/op
             p(90.0000) =    100.139 ms/op
             p(95.0000) =    100.139 ms/op
             p(99.0000) =    100.139 ms/op
             p(99.9000) =    100.139 ms/op
             p(99.9900) =    100.139 ms/op
             p(99.9990) =    100.139 ms/op
             p(99.9999) =    100.139 ms/op
            p(100.0000) =    100.139 ms/op
        
        
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 5 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread
        # Benchmark mode: Single shot invocation time
        # Benchmark: org.xceptance.training.jmh.Training03.benchSingle
        
        # Run progress: 99.97% complete, ETA 00:00:00
        # Fork: 1 of 1
        # Warmup Iteration   1: 100.161 ms/op
        # Warmup Iteration   2: 100.183 ms/op
        # Warmup Iteration   3: 100.140 ms/op
        Iteration   1: 100.192 ms/op
        Iteration   2: 100.200 ms/op
        Iteration   3: 100.113 ms/op
        Iteration   4: 100.135 ms/op
        Iteration   5: 100.147 ms/op
        
        
        Result "org.xceptance.training.jmh.Training03.benchSingle":
          N = 5
          mean =    100.157 ±(99.9%) 0.143 ms/op
        
          Histogram, ms/op:
            [100.110, 100.115) = 1 
            [100.115, 100.120) = 0 
            [100.120, 100.125) = 0 
            [100.125, 100.130) = 0 
            [100.130, 100.135) = 0 
            [100.135, 100.140) = 1 
            [100.140, 100.145) = 0 
            [100.145, 100.150) = 1 
            [100.150, 100.155) = 0 
            [100.155, 100.160) = 0 
            [100.160, 100.165) = 0 
            [100.165, 100.170) = 0 
            [100.170, 100.175) = 0 
            [100.175, 100.180) = 0 
            [100.180, 100.185) = 0 
            [100.185, 100.190) = 0 
            [100.190, 100.195) = 1 
            [100.195, 100.200) = 1 
        
          Percentiles, ms/op:
              p(0.0000) =    100.113 ms/op
             p(50.0000) =    100.147 ms/op
             p(90.0000) =    100.200 ms/op
             p(95.0000) =    100.200 ms/op
             p(99.0000) =    100.200 ms/op
             p(99.9000) =    100.200 ms/op
             p(99.9900) =    100.200 ms/op
             p(99.9990) =    100.200 ms/op
             p(99.9999) =    100.200 ms/op
            p(100.0000) =    100.200 ms/op
        
        
        # Run complete. Total time: 00:00:26
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark                                     Mode  Cnt       Score    Error  Units
        Training03.benchThroughput                   thrpt    5       9.982 ±  0.003  ops/s
        Training03.benchAvg                           avgt    5  100182.424 ± 69.858  us/op
        Training03.benchSample                      sample   50     100.110 ±  0.027  ms/op
        Training03.benchSample:benchSample·p0.00    sample          100.008           ms/op
        Training03.benchSample:benchSample·p0.50    sample          100.139           ms/op
        Training03.benchSample:benchSample·p0.90    sample          100.139           ms/op
        Training03.benchSample:benchSample·p0.95    sample          100.139           ms/op
        Training03.benchSample:benchSample·p0.99    sample          100.139           ms/op
        Training03.benchSample:benchSample·p0.999   sample          100.139           ms/op
        Training03.benchSample:benchSample·p0.9999  sample          100.139           ms/op
        Training03.benchSample:benchSample·p1.00    sample          100.139           ms/op
        Training03.benchSingle                          ss    5     100.157 ±  0.143  ms/op

      */
}
