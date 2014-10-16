package pl.bilickib.ms;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;

@State(Scope.Thread)
public class SortBenchmark {
    private static final int SEED=123;
    private static final int MAX=256;
    private static final int SIZE=100_000;

    @Param(value = {"false", "true"})
    public boolean sorted;

    private int[] randomArray;

    @Setup
    public void setup() {
        randomArray=randomArray();
        if (sorted) {
            Arrays.sort(randomArray);
        }
    }

    public int[] randomArray() {
        Random r=new Random(SEED);
        int[] a=r.ints(SIZE, SEED, MAX).toArray();
        return a;
    }

    @Benchmark
    public int sumHalfElements() {
        return sumElemensAboveThreshold(randomArray, MAX / 2); //if not used may be optimized out
    }

    @Benchmark
    public int sumAllElements() {
        return sumElemensAboveThreshold(randomArray, MAX); //if not used may be optimized out
    }

    private int sumElemensAboveThreshold(int[] array,int threshold) {
        int sum=0;
        for (int i=0;i<SIZE;i++) {
            if (array[i] >= threshold)
                sum += array[i];
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                .jvmArgs("-Xms812m", "-Xmx812m") //we do not want heap resize during test
                .warmupIterations(1)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }

    /**
     * use primitives for arrays and values
     * - If you have a lot of data.
     * - If you operate on the data often.
     * - If you create data often.
     * - If data is long-living.
     *
     *  fastutil, PCJ, GNU Trove, Apache Mahout (ported COLT collections), Apache Primitive Collections.
     *  http://labs.carrotsearch.com/hppc-faq.html#why-yet-another-collections-package
     */


}
