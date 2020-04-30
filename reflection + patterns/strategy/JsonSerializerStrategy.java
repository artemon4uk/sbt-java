package strategy;
import java.lang.reflect.Field;

public class JsonSerializerStrategy implements SerializerStrategy {

    @Override
    public String startClassToken(Object o) {
        return "{";
    }

    @Override
    public String endClassToken(Object o) {
        return "}";
    }

    @Override
    public String simpleFieldToken(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        return "\"" + field.getName() + "\": " + field.get(o);
    }

    @Override
    public String startNotSimpleFieldToken(Field field) {
        return "\"" + field.getName() + "\": {";
    }

    @Override
    public String endNotSimpleFieldToken(Field field) {
        return "}";
    }

    @Override
    public String startCollectionToken(Field field) {
        return "\"" + field.getName() + "\": [";
    }

    @Override
    public String endCollectionToken(Field field) {
        return "]";
    }

    @Override
    public String collectionElementToken(Object element, int key) {
        return element.toString();
    }
}
