package handlers;

import strategy.SerializerStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class Context {
    private final StringWorker stringWorker;
    private final SerializerStrategy strategy;

    public Context(SerializerStrategy strategy) {
        this.strategy = strategy;
        this.stringWorker = new StringWorker("");
    }

    public String executeStrategy(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        stringWorker.addStartingPart(result, strategy.startClassToken(o));
        stringWorker.addInformativePart(result, handleFields(o));
        stringWorker.addEndPart(result, strategy.endClassToken(o));
        return result.toString();
    }

    private boolean isSimpleField(Field field) {
        var type = field.getType();
        return type.isPrimitive() || type == Double.class ||
                type == Float.class || type == Long.class ||
                type == Integer.class || type == Short.class ||
                type == Character.class || type == Byte.class ||
                type == Boolean.class || type == String.class;
    }

    protected String handleFields(Object o) throws IllegalAccessException {
        Collection<String> elements = new ArrayList<>();
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            String processedField = handleField(declaredField, o);
            elements.add(processedField);
        }
        return stringWorker.join(elements);
    }

    private String handleField(Field field, Object o) throws IllegalAccessException {
        if (isSimpleField(field)) {
            return (new SimpleFieldHandler(strategy)).handle(field, o);
        } else if (Collection.class.isAssignableFrom(field.getType())) {
            return (new CollectionFieldHandler(strategy, stringWorker)).handle(field, o);
        } else {
            return handleNotSimpleField(field, o); // так как используется рекурсивный вызов в этом методе
        }
    }

    private String handleNotSimpleField(Field field, Object o) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        field.setAccessible(true);
        stringWorker.addStartingPart(stringBuilder, strategy.startNotSimpleFieldToken(field));
        stringWorker.addInformativePart(stringBuilder, handleFields(field.get(o)));
        stringWorker.addEndPart(stringBuilder, strategy.endNotSimpleFieldToken(field));
        return stringBuilder.toString();
    }
}