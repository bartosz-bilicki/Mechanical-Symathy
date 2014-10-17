package pl.bilickib.ms;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.profile.HotspotCompilationProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import pl.bilickib.ms.synchronization.SynchronizedRunnable;

import java.util.concurrent.TimeUnit;

public class E04_SynchronizationBenchmark {

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public int synchronizedRunable() throws InterruptedException {
        SynchronizedRunnable r=new SynchronizedRunnable();
        new Thread(r).start();
        TimeUnit.SECONDS.sleep(1);
        return r.counter;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(E04_SynchronizationBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                .jvmArgs("-Xms812m", "-Xmx812m") //we do not want heap resize during test
                .warmupIterations(1)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();

        //https://gist.github.com/jboner/2841832 -memory accees timings
    }
}
