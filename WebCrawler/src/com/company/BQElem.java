package com.company;

/**
 * Created by rami on 3/23/2016.
 */
class BQElem {
    String url;
    int curDepth;

    BQElem(int curDepth, String url) {
        this.curDepth = curDepth;
        this.url = url;
    }

    void printMe() {
        StringBuilder str = new StringBuilder(""+Thread.currentThread().getName()+":"+curDepth);
        for (int i = 0; i < curDepth; i++) {
            str.append(" ");
        }
        str.append(url);
        System.out.println(str.toString());
    }
}
