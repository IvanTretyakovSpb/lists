package com.tretyakov.patterns.strategy;

/**
 * Concrete strategy for sending emails.
 */
class EmailSendingMethod implements SendingMethod {

    @Override
    public void send(String from, String to, String msg) {
        System.out.printf("Email from %s to %s\n", from, to);
    }
}
