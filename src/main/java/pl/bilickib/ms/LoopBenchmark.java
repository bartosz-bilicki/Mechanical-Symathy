package pl.bilickib.ms;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class LoopBenchmark {

    private static final int SIZE=120_000_000;
    //private static final int SIZE=10;

    @Benchmark
    public int[] forTestRandomAccess() {
        int a[] =new int[SIZE];
        for (int i=0;i<SIZE-2;i++) {
            a[i]+=a[SIZE-1-i];
        }
        return a; //if not used, may be optimized out
    }

    @Benchmark
    public int[] forTestSequentialAccess() {
        int a[] =new int[SIZE];
        for (int i=0;i<SIZE-2;i++) {
            a[i]+=a[i+1];
        }
        return a;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LoopBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                .jvmArgs("-Xms812m", "-Xmx812m") //we do not want heap resize during test
                .warmupIterations(1)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();

        //https://gist.github.com/jboner/2841832
    }
}
