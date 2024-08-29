package com.tretyakov.patterns.strategy;

/**
 * In Strategy pattern, algorithms executed in different branches are moved into their own classes called strategies,
 * all of which implement a common interface. Strategies represent behavior, not domain entities.
 * The pattern has several advantages:
 * - it allows you to choose an algorithm (behavior) at runtime;
 * - it isolates the code of algorithms from the other classes, thus simplifying the addition of new algorithms;
 * - it lets the algorithm vary independently from clients that use it.
 *
 *
 * This application needs to send messages to customers.
 * There are different ways to reach out to them: via SMS or e-mail.
 * In addition, new sending methods will be added in future (for instance, push notifications).
 * It would be nice to change the existing code as little as possible when adding new strategies.
 * According to the strategy pattern, we need to define a family of algorithms (sending methods).
 * Each algorithm must encapsulate logic to send a message using a concrete transport (SMS/email).
 */
public class Main {
    public static void main(String[] args) {
        final MessageSender sender = new MessageSender(); // create a message sender

        sender.setMethod(new EmailSendingMethod()); // set a concrete sending method
        sender.send("IvanTretyakov@gmail.com", "DimitriiOdintsov@gmail.com", "Hello!");

        sender.setMethod(new SmsSendingMethod()); // set another sending method
        sender.send("+7-911-555-44-33", "+7-921-999-88-77", "Hello!");
    }
}
