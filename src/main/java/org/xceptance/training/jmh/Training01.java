package org.xceptance.training.jmh;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Start default and simple, don't do anything
 * 
 * @author rschwietzke
 */
public class Training01
{
     @Benchmark
     public void bench()
     {
     }
     
     /*
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training01
      */
     
     /*
      * Results
      * 
        # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 5 iterations, 10 s each
        # Measurement: 5 iterations, 10 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Throughput, ops/time
        # Benchmark: org.xceptance.training.jmh.Training01.bench
        
        # Run progress: 0.00% complete, ETA 00:08:20
        # Fork: 1 of 5
        # Warmup Iteration   1: 1583355399.039 ops/s
        # Warmup Iteration   2: 1539214226.386 ops/s
        # Warmup Iteration   3: 1497187122.677 ops/s
        # Warmup Iteration   4: 1464143076.223 ops/s
        # Warmup Iteration   5: 1408890030.487 ops/s
        Iteration   1: 1400594747.986 ops/s
        Iteration   2: 1443236681.579 ops/s
        Iteration   3: 1394508697.454 ops/s
        Iteration   4: 
        1423377056.796 ops/s
        Iteration   5: 1418124423.941 ops/s
        
        # Run progress: 20.00% complete, ETA 00:06:41
        # Fork: 2 of 5
        # Warmup Iteration   1: 1484993672.027 ops/s
        # Warmup Iteration   2: 1606297235.159 ops/s
        # Warmup Iteration   3: 1531173584.001 ops/s
        # Warmup Iteration   4: 1515990273.715 ops/s
        # Warmup Iteration   5: 1505060990.003 ops/s
        Iteration   1: 1524299479.880 ops/s
        Iteration   2: 1528673828.510 ops/s
        Iteration   3: 1525227073.567 ops/s
        Iteration   4: 1507692928.347 ops/s
        Iteration   5: 1482111526.372 ops/s
        
        # Run progress: 40.00% complete, ETA 00:05:01
        # Fork: 3 of 5
        # Warmup Iteration   1: 1572224524.842 ops/s
        # Warmup Iteration   2: 1551741195.538 ops/s
        # Warmup Iteration   3: 1505343788.090 ops/s
        # Warmup Iteration   4: 1617260422.916 ops/s
        # Warmup Iteration   5: 1499985427.378 ops/s
        Iteration   1: 1489210843.170 ops/s
        Iteration   2: 1551490556.690 ops/s
        Iteration   3: 1549594169.817 ops/s
        Iteration   4: 1543952938.006 ops/s
        Iteration   5: 1555352808.937 ops/s
        
        # Run progress: 60.00% complete, ETA 00:03:20
        # Fork: 4 of 5
        # Warmup Iteration   1: 1611274742.230 ops/s
        # Warmup Iteration   2: 1566906378.448 ops/s
        # Warmup Iteration   3: 1593623312.362 ops/s
        # Warmup Iteration   4: 1604372603.178 ops/s
        # Warmup Iteration   5: 1571141486.187 ops/s
        Iteration   1: 1541876651.013 ops/s
        Iteration   2: 1512864106.254 ops/s
        Iteration   3: 1496557287.336 ops/s
        Iteration   4: 1596375227.063 ops/s
        Iteration   5: 1394920757.987 ops/s
        
        # Run progress: 80.00% complete, ETA 00:01:40
        # Fork: 5 of 5
        # Warmup Iteration   1: 1531913004.291 ops/s
        # Warmup Iteration   2: 1456034608.512 ops/s
        # Warmup Iteration   3: 1591922923.401 ops/s
        # Warmup Iteration   4: 1571500846.336 ops/s
        # Warmup Iteration   5: 1531698781.313 ops/s
        Iteration   1: 1557312487.759 ops/s
        Iteration   2: 1384351733.148 ops/s
        Iteration   3: 1543160459.617 ops/s
        Iteration   4: 1554555916.234 ops/s
        Iteration   5: 1531854338.713 ops/s
        
        
        Result "org.xceptance.training.jmh.Training01.bench":
          1498051069.047 ±(99.9%) 46913978.289 ops/s [Average]
          (min, avg, max) = (1384351733.148, 1498051069.047, 1596375227.063), stdev = 62628818.796
          CI (99.9%): [1451137090.758, 1544965047.336] (assumes normal distribution)
        
        
        # Run complete. Total time: 00:08:22
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark          Mode  Cnt           Score          Error  Units
        Training01.bench  thrpt   25  1498051069.047 ± 46913978.289  ops/s
      */
}
