package atomic.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author zhwanwan
 * @create 2019-07-03 6:23 PM
 */
public class Test01 {

    public static void main(String[] args) {
        AtomicInteger idx = new AtomicInteger();
        int[] array = IntStream.rangeClosed(0, 9).toArray();
        IntStream.rangeClosed(0, 9).forEach(i -> {
            //减号(-)的优先级高于&
            System.out.println(idx.getAndIncrement() & array.length - 1);
        });
    }

}
