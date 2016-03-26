package com.company;

import java.io.*;
public class SwappingNumberWithoutThirdVariable
{
    public static void main(String[] args)throws IOException
    {
        int a = 3 ,b = 5;

        System.out.println("a = "+a);
        System.out.println("b = "+b);

        //Beginning of Swapping
        a = a + b;
        b = a - b;
        a = a - b;
        //End of Swapping

        System.out.println("The numbers after swapping are");
        System.out.println("a = "+a);
        System.out.println("b = "+b);
    }
}