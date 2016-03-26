package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rami on 3/22/2016.
 */
public class Fibonacci {

    public static void main(String[] args)  {
        int fibLimit = Integer.valueOf(args[0]);
        System.out.println(" 0\n 1 ");
//        List<Integer> l1 = Arrays.asList(0, 1);
        fibo1(0, 1, 2, fibLimit);
        fibo2(0, 1, fibLimit);
    }

    private static void fibo2(int i1, int i2, int fibLimit) {
        int i3 = -1;
        for (int i = 0; i < fibLimit-2; i++) {
            i3 = i1 +i2;
            i1=i2;
            i2=i3;
            System.out.print(" "+ i3);

        }
    }

    private static void fibo1(int i1, int i2, int curIndex, int fibLimit) {
        if (++curIndex <= fibLimit) {
            System.out.println(" "+ (i2 + i1));
            fibo1(i2, i1+i2, curIndex, fibLimit );
        }

    }
}