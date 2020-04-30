package strategy;

import java.lang.reflect.Field;

public interface SerializerStrategy {
    String startClassToken(Object o);

    String endClassToken(Object o);

    String simpleFieldToken(Field field, Object o) throws IllegalAccessException;

    String startNotSimpleFieldToken(Field field);

    String endNotSimpleFieldToken(Field field);

    String startCollectionToken(Field field);

    String endCollectionToken(Field field);

    String collectionElementToken(Object element, int key);
}

