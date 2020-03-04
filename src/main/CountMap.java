package main;

import java.util.Map;

public interface CountMap<E> {

    void add(E key);

    int getCount(E key);

    int remove(E key);

    int size();

    void addAll(CountMap<? extends E> source);

    Map<E, Integer> toMap();

    void toMap(Map<? super E, Integer> destination);


}
