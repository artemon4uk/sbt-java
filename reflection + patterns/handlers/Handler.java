package handlers;

import java.lang.reflect.Field;

public interface Handler {
    String handle(Field field, Object o) throws IllegalAccessException;
}
