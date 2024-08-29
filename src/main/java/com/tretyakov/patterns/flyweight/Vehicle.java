package com.tretyakov.patterns.flyweight;

import java.awt.*;

/**
 * Interface for a vehicle.
 */
public interface Vehicle {
    public void start();
    public void stop();
    public Color getColor();
}
