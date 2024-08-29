package com.tretyakov.patterns.flyweight;

import java.awt.*;

/**
 * Flyweight is a structural design pattern that allows us
 * to use the same elements in different contexts to lower RAM usage.
 * Flyweight - a pattern that increases the number of objects
 * we can fit into memory by sharing and reusing their common parts.
 * This pattern applies to a project with a large number of objects to fit them all into your memory.
 * This pattern's purpose is to store common parts of multiple objects in a single class
 * instead of keeping data inside of each object.
 * It’s very important that the flyweight objects are immutable:
 * any operation on the state must be performed by the factory.
 */
public class MyFlyweight {
    public static void main(String[] args) {

        Vehicle blackVehicle = VehicleFactory.createVehicle(Color.BLACK);
        Vehicle redVehicle = VehicleFactory.createVehicle(Color.RED);
        Vehicle anotherBlackVehicle = VehicleFactory.createVehicle(Color.BLACK);
        Vehicle anotherRedVehicle = VehicleFactory.createVehicle(Color.RED);


        // Objects returned by the factory are NOT equals!
        System.out.println("blackVehicle == redVehicle -> " + (blackVehicle == redVehicle));
        // Objects returned by the factory are equals!
        System.out.println("blackVehicle == anotherBlackVehicle -> " + (blackVehicle == anotherBlackVehicle));
        // Objects returned by the factory are NOT equals!
        System.out.println("redVehicle == anotherBlackVehicle -> " + (redVehicle == anotherBlackVehicle));
        // Objects returned by the factory are equals!
        System.out.println("redVehicle == anotherRedVehicle -> " + (redVehicle == anotherRedVehicle));
    }
}
