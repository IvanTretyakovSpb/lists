package com.tretyakov.patterns.singleton;

public class Main {
    public static void main(String[] args) {
        MySingleton s1 = MySingleton.getInstance();
        MySingleton s2 = MySingleton.getInstance();
        MySingleton s3 = MySingleton.getInstance();

        System.out.println("s1 == s2 -> " + (s1 == s2)); // true because s1 and s2 refer the same object
        System.out.println("s1 == s3 -> " + (s1 == s3)); // true because s1 and s3 refer the same object
        System.out.println("s2 == s3 -> " + (s2 == s3)); // true because s2 and s3 refer the same object
    }
}
