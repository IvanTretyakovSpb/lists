package com.tretyakov.patterns.singleton;

/**
 * It is a thread-safe implementation of lazy initialization.
 */
public class ThreadSafeSingleton {

    // A private static variable of the class that is the only instance of the class
    // The volatile keyword means the changes made by one thread are instantly visible
    // to all the other threads accessing the variable.
    // Here it will ensure that all threads are working with updated values.
    private static volatile ThreadSafeSingleton instance;

    // Some additional instance fields to store values
    // to share them across application as well as methods to have a behavior.
    // ...
    // ...
    // ...

    // A private constructor to defeat the creation of instances using the keyword new
    private ThreadSafeSingleton() {

    }

    // A public static method to return the same instance of the class.
    // This is a global access point to the instance.
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) { // outer if condition
            // Lazy initialization.
            // A synchronized block in layman's terms means that if thread A is accessing singletonObject,
            // all other threads have to wait till thread A has finished processing singletonObject.
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) { // inner if condition
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    // Other utility methods & variables
}
