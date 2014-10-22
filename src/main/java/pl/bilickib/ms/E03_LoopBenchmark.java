package pl.bilickib.ms;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.HotspotCompilationProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class E03_LoopBenchmark {

    private static final int SIZE=120_000_000;
    //private static final int SIZE=10;

    @Benchmark
    public int[] forTestSequentialAccess() {
        int a[] =new int[SIZE];
        for (int i=0;i<SIZE-2;i++) {
            a[i]+=a[i+1];
        }
        return a;
    }

    @Benchmark
    public int[] forTestRandomAccess() {
        int a[] =new int[SIZE];
        for (int i=0;i<SIZE-2;i++) {
            a[i]+=a[SIZE-1-i];
        }
        return a; //if not used, may be optimized out
    }



    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(E03_LoopBenchmark.class.getSimpleName())
                .forks(1)
                .shouldDoGC(true)
                .jvmArgs("-Xms812m", "-Xmx812m","-XX:+UnlockDiagnosticVMOptions","-XX:+PrintCompilation") //we do not want heap resize during test
                //,"-XX:+PrintInlining"
                /* timestamp compilation_id attributes (tiered_level) method_name size
                    attributes
                    % - on stack replacement
                    s - synchronized
                    ! exception handler
                    b compilation in blocking mode
                    n compilation for a wrapper to a native method
                 */
                /*
                beware of cache code size (32-240m). depennds of 32/64 bits, java 7/8, tiered compilations
                -XX:initalCacheCodeSize=N
                -XX:ReservedCodeCacheSize=N
                */
                /* beware process size (4GB on 32bit)
                    heap, JVM code, native libraries, thread stacks, native memory (NIO), code cache, metadata
                */

                /*
                tiered compilation
                0 - interpreted
                1 : simple C1
                2: limited C1
                3: full C1
                4: C2
                 */

                        /**
                         * deoptimizing
                         * removing not entrant code
                         * escapa analizys
                         * inlining
                         * compliation threads
                         * compilation thresholds
                         */

                .warmupIterations(1)
                .mode(Mode.SingleShotTime)
                .addProfiler(HotspotCompilationProfiler.class)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();

        //https://gist.github.com/jboner/2841832 -memory accees timings
    }
}
