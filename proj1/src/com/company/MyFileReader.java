package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by rami on 3/26/2016.
 */
public class MyFileReader {

    public static void main(String[] args) throws IOException {
        String myStr = String.join("\n", Files.readAllLines(Paths.get("C:\\dev\\java\\java_pocs\\proj1\\src\\com\\company\\InTxtFile.txt")));
        System.out.println(String.format("myStr = %s", myStr));
    }
}
