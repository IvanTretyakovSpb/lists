package com.tretyakov.patterns.strategy;

/**
 * The special class called Context for storing a reference to a strategy.
 * The context delegates execution to an instance of a concrete strategy through its interface,
 * instead of implementing the behavior itself.
 */
class MessageSender {

    private SendingMethod method;

    // it may contain additional fields as well

    public void setMethod(SendingMethod method) {
        this.method = method;
    }

    public void send(String from, String to, String msg) {
        this.method.send(from, to, msg);
    }
}
