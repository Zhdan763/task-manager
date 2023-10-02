package com.javalab.controller;


public class IdGenerator {
    private static IdGenerator idGenerator;
    private int counter;

    private IdGenerator() {
        this.counter = 0;
    }

    private IdGenerator(int startValue) {
        this.counter = startValue;
    }

    public static synchronized IdGenerator getIdGenerator() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public static synchronized IdGenerator getIdGenerator(int startValue) {
        if (idGenerator == null) {
            idGenerator = new IdGenerator(startValue);
        }
        return idGenerator;
    }

    public int getNextId() {
        return counter++;
    }

}
