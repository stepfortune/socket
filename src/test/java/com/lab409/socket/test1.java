package com.lab409.socket;

public class test1 {
    public native void predict();
    static {
        System.loadLibrary("darknet");
    }
    public static void main(String[] args) {
        new test1().predict();
    }
}
