package com.tretyakov.patterns.strategy;

/**
 * Concrete strategy for sending SMS.
 */
class SmsSendingMethod implements SendingMethod {

    @Override
    public void send(String from, String to, String msg) {
        System.out.printf("Send SMS from %s to %s\n", from, to);
    }

}
