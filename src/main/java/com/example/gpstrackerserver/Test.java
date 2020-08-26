package com.example.gpstrackerserver;


public class Test {
    private void fun(String string) {
        string = "2222222222";
    }

    public static void main(String[] args) {
        String s = "111";
        new Test().fun(s);
        System.out.println(s);
    }
}
