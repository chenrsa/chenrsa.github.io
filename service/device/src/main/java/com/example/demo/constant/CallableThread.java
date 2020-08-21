package com.example.demo.constant;

import java.util.concurrent.Callable;

/**
 * @author chenrunzheng
 * @since 2020/7/13 10:47
 */
public class CallableThread  implements Callable<Integer> {

        public Integer call() throws Exception {
            int sum = 0;

            for (int i = 0; i <= 1000; i++) {
                sum += i;
            }

            return sum;
        }

}
