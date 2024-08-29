package com.tretyakov.patterns.singleton;

/**
 * Singleton is a creational design pattern that allows you to keep only one instance of the class and
 * provides global access to it for the outer world.
 * The basic singleton implementation only in one-thread environments because it is prone to multithreading issues.
 */
public class MySingleton {

    // A private static variable of the class that is the only instance of the class
    private static MySingleton instance;

    // Some additional instance fields to store values
    // to share them across application as well as methods to have a behavior.
    // ...
    // ...
    // ...

    // A private constructor to defeat the creation of instances using the keyword new
    private MySingleton() {

    }

    // A public static method to return the same instance of the class.
    // This is a global access point to the instance.
    public static MySingleton getInstance() {
        if (instance == null) {
            // Lazy initialization.
            // The singleton instance is not created until the getInstance method is called for the first time.
            instance = new MySingleton();
        }
        return instance;
    }
}
