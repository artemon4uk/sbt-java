package handlers;
import strategy.SerializerStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class CollectionFieldHandler implements Handler {

    private final SerializerStrategy strategy;
    private final StringWorker stringWorker;

    public CollectionFieldHandler(SerializerStrategy strategy, StringWorker stringWorker) {
        this.strategy = strategy;
        this.stringWorker = stringWorker;
    }

    @Override
    public String handle(Field field, Object o) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringWorker.addStartingPart(stringBuilder, strategy.startCollectionToken(field));
        stringWorker.addInformativePart(stringBuilder, handleCollectionElements(field, o));
        stringWorker.addEndPart(stringBuilder, strategy.endCollectionToken(field));
        return stringBuilder.toString();
    }

    private String handleCollectionElements(Field field, Object o) throws IllegalAccessException {
        int i = 1;
        field.setAccessible(true);
        var collection = field.get(o);
        ArrayList<String> elements = new ArrayList<>();
        for (Object element : (Collection<?>) collection) {
            String processedElement = strategy.collectionElementToken(element, i++);
            elements.add(processedElement);
        }
        return stringWorker.join(elements);
    }
}
