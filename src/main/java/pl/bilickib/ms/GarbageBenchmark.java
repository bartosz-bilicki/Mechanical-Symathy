package pl.bilickib.ms;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.profile.HotspotRuntimeProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class GarbageBenchmark {

    private static final int SIZE=40_000_000;

    @Benchmark
    public  int[] intArrayIntegerValue() {
        int[] ret=new int[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=new Integer(i);;
        }
        return ret;
    }

    @Benchmark
    public  int[] intArrayIntValue() {
        int[] ret=new int[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=i;
        }
        return ret;
    }

    //@Benchmark
    public  int[] intArrayIntegerValueOfValue() {
        int[] ret=new int[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=Integer.valueOf(i);
        }
        return ret;
    }

    //@Benchmark
    public  Integer[] integerArrayIntegerValue() {
        Integer[] ret=new Integer[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=new Integer(i);;
        }
        return ret;
    }

    //@Benchmark
    public  Integer[] integerArrayIntValue() {
        Integer[] ret=new Integer[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=i;
        }
        return ret;
    }

    //@Benchmark
    public  Integer[] integerArrayIntegerValueOfValue() {
        Integer[] ret=new Integer[SIZE];
        for (int i=0;i<SIZE;i++) {
            ret[i]=Integer.valueOf(i);
        }
        return ret;
    }



    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(GarbageBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                //.jvmArgs("-Xms300m", "-Xmx812m", "-verbose:gc", "-XX:+PrintGCTimeStamps", "-XX:+PrintGCDetails")
                .jvmArgs("-Xms300m","-Xmx812m")
                .warmupIterations(1)
                .measurementIterations(5)
                .addProfiler(GCProfiler.class) //formating bug. ingore ,000 for gc.time.
                .mode(Mode.SingleShotTime)
                //we are interested in gc.count.profiled, tc.time.profiled- thhose are GC count and time during test
                //.addProfiler(HotspotMemoryProfiler.class).addProfiler(HotspotRuntimeProfiler.class) //useless
                .build();

        new Runner(opt).run();
    }
}
