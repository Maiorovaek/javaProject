package collections.Tests;

import collections.figure.Circle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestMap {
    int n = 10000;
    public static void main(String[] args) {
        TestMap testMap = new TestMap();

        Map<Integer, Circle> hashMap = new HashMap<>();
        Map<Integer, Circle> linkedHashMap = new LinkedHashMap<>();
        Map<Integer, Circle> treeMap = new TreeMap<>();

        System.out.println("Add element");
        testMap.timeCalc(testMap.putEndMap(hashMap), testMap.putEndMap(linkedHashMap), testMap.putEndMap(treeMap));
        System.out.println("Remove element in middle");
        testMap.timeCalc(testMap.remove(hashMap), testMap.remove(linkedHashMap), testMap.remove(treeMap));

        System.out.println("Add element in Middle");
        testMap.timeCalc(testMap.addElementMiddle(hashMap), testMap.addElementMiddle(linkedHashMap), testMap.addElementMiddle(treeMap));

    }


    public void create(Map<Integer, Circle> map) {
        for (int i =0; i<n;i++) {
        map.put(i, new Circle());
        }
    }

    public long putEndMap(Map<Integer, Circle>  map){
        TestMap testMap = new TestMap();
        testMap.create(map);
        long start = System.nanoTime();
        map.put(n-1, new Circle());
        long end = System.nanoTime() - start;
        return end;
    }

    public long remove(Map<Integer, Circle> map){
        long start = System.nanoTime();
        map.remove(n-1);
        long end = System.nanoTime() - start;
        return end;

    }

    public long addElementMiddle(Map<Integer,Circle> map){
        long start = System.nanoTime();
        map.put(n/2, new Circle());
        long end = System.nanoTime() - start;
        return end;
    }


    public void timeCalc(long time1, long time2, long time3) {
        if (time1 < time2 && time1<time3) {
            System.out.println("HashMap faster then other " + time1 + " " + time2 + " " + time3);
        } else if(time2<time1 && time2<time3) {
            System.out.println("LinkedHashMap faster then other " + time1 + " " + time2 + " " + time3 );

        } else if(time3<time1 && time3<time2){
            System.out.println("TreeMap faster then other "  + time1 + " " + time2 + " " + time3);
        }
    }
    }

