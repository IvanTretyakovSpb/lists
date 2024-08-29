package com.tretyakov.patterns.strategy;

/**
 * The common Strategy interface for a family of algorithms.
 */
interface SendingMethod {

    void send(String from, String to, String msg);

}
