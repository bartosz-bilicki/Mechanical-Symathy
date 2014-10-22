package pl.bilickib.ms;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;

@State(Scope.Thread)

public class E01_SortBenchmark {
    private static final int SEED=123;
    private static final int MAX=256;
    private static final int SIZE=1_000_000;

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
                .include(E01_SortBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                .jvmArgs("-Xms812m", "-Xmx812m") //we do not want heap resize during test
                .warmupIterations(1)
                //.mode(Mode.SingleShotTime)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }
}








































/*
the difference between an L1D cache-hit, and a full miss resulting in main-memory access,
        is 2 orders of magnitude; i.e. <1ns vs. 65-100ns

Translation Lookaside Buffers (TLBs)
    caches (part of) page table
    page table translate virtual memory address to phisical memory address.
    (all memory allocation is in pages)
    bigger pages= more chance of TLB hit.
    TLB miss=delay
*/