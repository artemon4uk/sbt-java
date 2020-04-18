package test;

import main.CountMap;
import main.CountMapImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class CountMapImplTest {

    @Test
    public void add() {
        CountMap<String> counter = new CountMapImpl<>();
        counter.add("aa");
        counter.add("bb");
        counter.add("aa");
        counter.add("aa");
        counter.add("bb");
        counter.add("aa");
        counter.add("cc");

        Assert.assertEquals(4, counter.getCount("aa"));
        Assert.assertEquals(2, counter.getCount("bb"));
        Assert.assertEquals(1, counter.getCount("cc"));
        Assert.assertEquals(0, counter.getCount("abc"));
    }

    @Test
    public void remove() {
        CountMap<String> counter = new CountMapImpl<>();
        counter.add("aa");
        counter.add("aa");

        Assert.assertEquals(2, counter.getCount("aa"));
        counter.remove("rehtb");

        Assert.assertEquals(2, counter.getCount("aa"));
        counter.remove("aa");

        Assert.assertEquals(0, counter.getCount("aa"));
        counter.remove("aa");

        Assert.assertEquals(0, counter.getCount("aa"));
    }

    @Test
    public void size() {
        CountMap<String> counter = new CountMapImpl<>();
        Assert.assertEquals(0, counter.size());

        counter.add("aa");
        Assert.assertEquals(1, counter.size());

        counter.add("aa");
        Assert.assertEquals(1, counter.size());

        counter.add("bb");
        Assert.assertEquals(2, counter.size());

        counter.remove("ascv");
        Assert.assertEquals(2, counter.size());

        counter.remove("aa");
        Assert.assertEquals(1, counter.size());
    }

    @Test
    public void addAll() {
        CountMap<String> counter1 = new CountMapImpl<>();
        CountMap<String> counter2 = new CountMapImpl<>();

        counter1.add("aa");
        counter1.add("aa");
        counter1.add("bb");

        counter2.add("aa");
        counter2.add("bb");
        counter2.add("cc");

        counter1.addAll(counter2);
        Assert.assertEquals(3, counter1.getCount("aa"));
        Assert.assertEquals(2, counter1.getCount("bb"));
        Assert.assertEquals(1, counter1.getCount("cc"));
        Assert.assertEquals(0, counter1.getCount("dd"));


    }

    @Test
    public void toMap() {
        CountMap<String> counter = new CountMapImpl<>();
        counter.add("aa");
        counter.add("aa");
        counter.add("aa");
        counter.add("bb");
        counter.add("bb");
        counter.add("cc");

        Map<String, Integer> resMap = counter.toMap();
        Map<String, Integer> answerMap = new TreeMap<>();

        answerMap.put("aa", 3);
        answerMap.put("bb", 2);
        answerMap.put("cc", 1);
        Assert.assertEquals(resMap, answerMap);
    }

    @Test
    public void testToMap() {
        CountMap<String> counter = new CountMapImpl<>();
        counter.add("aa");
        counter.add("aa");
        counter.add("aa");
        counter.add("bb");
        counter.add("bb");
        counter.add("cc");

        Map<String, Integer> resMap = new TreeMap<>();
        Map<String, Integer> answerMap = new TreeMap<>();
        counter.toMap(resMap);

        answerMap.put("aa", 3);
        answerMap.put("bb", 2);
        answerMap.put("cc", 1);
        Assert.assertEquals(resMap, answerMap);
    }
}