package collections.Tests;


import java.util.*;

public class TestSet {
    private int n = 10000;
    public static void main(String[] args) {
        TestSet testSet = new TestSet();

        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        System.out.println("Add element");
        testSet.timeCalc(testSet.addEndSet(hashSet), testSet.addEndSet(linkedHashSet), testSet.addEndSet(treeSet));
        System.out.println("Remove element in middle");
        testSet.timeCalc(testSet.removeElement(hashSet), testSet.removeElement(linkedHashSet), testSet.removeElement(treeSet));
        System.out.println("Contains element");
        testSet.timeCalc(testSet.containsElenents(hashSet), testSet.containsElenents(linkedHashSet), testSet.containsElenents(treeSet));
    }


    void createSet(Set<Integer> set) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            set.add(random.nextInt());
        }
    }


    public long addEndSet(Set<Integer> set) {
        TestSet testS = new TestSet();
        testS.createSet(set);
        long start = System.nanoTime();
        set.add(2800000);
        long end = System.nanoTime() - start;
        return end;
    }



    public long removeElement(Set<Integer> set) {
        long start = System.nanoTime();
        set.remove(n - 1);
        long end = System.nanoTime() - start;
        return end;
    }

    public long containsElenents(Set<Integer> set){
        long start = System.nanoTime();
        set.contains(5800);
        long end = System.nanoTime() - start;
        return end;
    }



    public void timeCalc(long time1, long time2, long time3) {
        if (time1 < time2 && time1<time3) {
            System.out.println("HashSet faster then other " + time1 + " " + time2 + " " + time3);
        } else if(time2<time1 && time2<time3) {
            System.out.println("LinkedHashSet faster then other " + time1 + " " + time2 + " " + time3 );

        } else if(time3<time1 && time3<time2){
            System.out.println("TreeSet faster then other"  + time1 + " " + time2 + " " + time3);

        }


    }
}
