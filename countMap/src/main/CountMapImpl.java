package main;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {

    private Map<E, Integer> counter = new HashMap<>();

    @Override
    public void add(E key) {
        counter.put(key, counter.containsKey(key) ? getCount(key) + 1 : 1);
    }

    @Override
    public int getCount(E key) {
        return counter.getOrDefault(key, 0);
    }

    @Override
    public int remove(E key) {
        if (counter.containsKey(key)) {
            return counter.remove(key);
        } else {
            return 0;
        }
    }

    @Override
    public int size() {
        return counter.size();
    }

    public void add(E key, int count) {
        counter.put(key, counter.containsKey(key) ? getCount(key) + count : count);
    }

    @Override
    public void addAll(CountMap<? extends E> source) {
        for (Map.Entry<? extends E, Integer> eIntegerEntry : source.toMap().entrySet()) {
            add(eIntegerEntry.getKey(), eIntegerEntry.getValue());
        }
    }

    @Override
    public Map<E, Integer> toMap() {
        return counter;
    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination.clear();
        destination.putAll(counter);
    }
}
