package handlers;

import strategy.SerializerStrategy;

import java.lang.reflect.Field;

public class SimpleFieldHandler implements Handler {
    private final SerializerStrategy strategy;

    public SimpleFieldHandler(SerializerStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String handle(Field field, Object o) throws IllegalAccessException {
        return strategy.simpleFieldToken(field, o);
    }
}
