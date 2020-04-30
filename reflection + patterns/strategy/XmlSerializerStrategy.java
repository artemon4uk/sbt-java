package strategy;
import java.lang.reflect.Field;

public class XmlSerializerStrategy implements SerializerStrategy {

    @Override
    public String startClassToken(Object o) {
        return "<" + o.getClass().getSimpleName() + ">";
    }

    @Override
    public String endClassToken(Object o) {
        return startClassToken(o);
    }

    @Override
    public String simpleFieldToken(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        return "<" + field.getName() + ">" + field.get(o) + "</" + field.getName() + ">";
    }

    @Override
    public String startNotSimpleFieldToken(Field field) {
        return "<" + field.getName() + ">";
    }

    @Override
    public String endNotSimpleFieldToken(Field field) {
        return startNotSimpleFieldToken(field);
    }

    @Override
    public String startCollectionToken(Field field) {
        return "<" + field.getName() + ">";
    }

    @Override
    public String endCollectionToken(Field field) {
        return startCollectionToken(field);
    }

    @Override
    public String collectionElementToken(Object element, int key) {
        return "<" + key + ">" + element + "</" + key + ">";
    }
}
