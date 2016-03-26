package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by rami on 3/26/2016.
 */
public class MyFileReaderWriter {

    public static void main(String[] args) throws IOException {
        String myStr = String.join("\n", Files.readAllLines(Paths.get("C:\\dev\\java\\java_pocs\\proj1\\src\\com\\company\\InTxtFile.txt")));
        System.out.println(String.format("myStr = %s", myStr));

        PrintWriter writer = new PrintWriter("C:\\dev\\java\\java_pocs\\proj1\\src\\com\\company\\OutTxtFile.txt", "UTF-8");
        writer.println(myStr);
        writer.close();

    }
}
