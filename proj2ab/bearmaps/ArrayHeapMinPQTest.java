package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void basicTest() {
        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 5; i++) {
            ex.add(i, i + 5);
        }
        ex.add(5, 0.8);
        ex.add(6, 0.5);
        assertTrue(ex.contains(1));
        assertFalse(ex.contains(13));
        assertEquals((int) ex.getSmallest(), 6);
        assertNotEquals((int) ex.getSmallest(), 0);
        assertEquals((int) ex.removeSmallest(), 6);
        assertEquals((int) ex.getSmallest(), 5);
        ex.removeSmallest();
        assertEquals((int) ex.getSmallest(), 0);
        assertEquals(ex.size(), 5);
        ex.removeSmallest();
        assertEquals((int) ex.getSmallest(), 1);

        ArrayHeapMinPQ<Integer> ex1 = new ArrayHeapMinPQ<>();
        ex1.add(90, 10.0);
        ex1.add(80, 11.0);
        ex1.add(60, 13.0);
        ex1.add(50, 15.0);
        ex1.add(30, 1.0);
        ex1.add(10, 0.0);
        ex1.add(33, 2.0);
        assertTrue(ex1.contains(10));
        assertFalse(ex1.contains(13));
    }

    @Test
    public void exceptionTest() {
        /** test for exceptions arised from remove operations */
        ArrayHeapMinPQ<String> ex = new ArrayHeapMinPQ<>();
        ex.add("cat", 25.0);
        ex.add("rat", 1.0);
        ex.removeSmallest();
        ex.removeSmallest();
        ex.add("deer", 100.0);
        assertFalse(ex.contains("cat"));
        assertEquals(ex.removeSmallest(), "deer");
        //ex.removeSmallest(); //exception arises here
        //ex.getSmallest(); //exception arises here
        //ex.removeSmallest(); //exception arises here
        /** Array cleared up to default */

        /** Test for exceptions derived from add operations */
        ex.add("cat", 45.0);
        ex.add("elephant", 500.0);
        ex.add("torquiose", 105.0);
        ex.add("rabbit", 30.0);
        ex.add("rat", 5.0);
        ex.add("squirrel", 15.0);
        ex.add("t-rex", 100000.0);
        //ex.add("bacteria", 1.0);
        //adding an item with the same priority
        ex.add("turtle", 105.0);
        ex.changePriority("torquiose", 150);
        assertEquals(ex.getSmallest(), "rat");
        assertEquals(ex.removeSmallest(), "rat");
        assertEquals(ex.getSmallest(), "squirrel");
        assertEquals(ex.removeSmallest(), "squirrel");
        assertEquals(ex.removeSmallest(), "rabbit");
        assertEquals(ex.removeSmallest(), "cat");
        assertEquals(ex.removeSmallest(), "turtle");
        assertEquals(ex.removeSmallest(), "torquiose");
        assertEquals(ex.removeSmallest(), "elephant");
        assertEquals(ex.removeSmallest(), "t-rex");
        //ex.removeSmallest();
        ex.add("cat", 45.0);
        ex.removeSmallest();
        ex.add("cat", 45.0);
        ex.add("elephant", 500.0);
        ex.add("torquiose", 105.0);
        ex.add("rabbit", 30.0);
        ex.add("rat", 5.0);
        ex.add("squirrel", 15.0);
    }


    @Test
    public void inRandomTest() {
        /**Test add in randomized context */
        ArrayHeapMinPQ<Integer> se = new ArrayHeapMinPQ<>();
        se.add(-2, 2);
        se.add(-6, 11);
        se.add(0, -15);
        se.add(4, 19);
        se.add(-9, -7);
        se.add(1, 9);
        se.add(-7, 13);
        se.add(6, -16);
        se.add(-5, 5);
        se.add(-10, 12);
        se.add(3, 16);
        se.add(2, -5);
        se.add(-3, -20);
        se.add(7, -11);
        se.add(5, 17);
        se.add(-8, -3);
        se.add(9, 18);
        se.add(-1, -18);
        se.add(10, 7);
        se.add(-4, 15);
        se.add(8, 0);
        se.changePriority(2, 9);

        for (int i = 0; i <= 20; i++) {
            System.out.println(se.removeSmallest());
        }
        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        int size = 21;
        ArrayList<Integer> itemRandomBox = new ArrayList<>(size);
        ArrayList<Integer> priorityRandomBox = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = -10; i < 11; i++) {
            itemRandomBox.add(i);
        }
        for (int i = -20; i < 21; i++) {
            priorityRandomBox.add(i);
        }
        for (int i = 0; i <= 20; i++) {
            int item = itemRandomBox.remove(rand.nextInt(itemRandomBox.size()));
            int prior = priorityRandomBox.remove(rand.nextInt(priorityRandomBox.size()));
            System.out.println("item: " + item + ", priority: " + prior);
            ex.add(item, prior);
        }
        ex.changePriority(2, 9);
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());
        System.out.println(ex.removeSmallest());



    }
    @Test
    public void validTest() {
        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        ex.add(0, 23); //4
        ex.add(1, 138); //10
        ex.add(8, 199); //20
        ex.add(16, 2); //1
        ex.add(9, 171.8); //15
        ex.add(15, 196); //18
        ex.add(14, 177); //17
        ex.add(12, 154); //12
        ex.add(10, 98); //8
        ex.add(11, 157); //14
        ex.add(7, 14); //2
        ex.add(13, 155); //13
        ex.add(17, 148); //11
        ex.add(2, 70); //5
        ex.add(3, 92); //7
        ex.add(4, 77); //6
        ex.add(5, 99); //9
        ex.add(6, 15); //3
        ex.add(19, 171.9); //16
        ex.add(18, 198); //19
        ex.add(20, 196.5);
        ex.add(500, -1000.0);
        assertEquals((int) ex.removeSmallest(), 500);
        assertEquals((int) ex.removeSmallest(), 16);
        assertEquals((int) ex.removeSmallest(), 7);
        assertEquals((int) ex.removeSmallest(), 6);
        assertEquals((int) ex.removeSmallest(), 0);
        assertEquals((int) ex.removeSmallest(), 2);
        ex.changePriority(4, 100); //change!!!
        assertEquals((int) ex.removeSmallest(), 3);
        assertEquals((int) ex.removeSmallest(), 10);
        assertEquals((int) ex.removeSmallest(), 5);
        assertEquals((int) ex.removeSmallest(), 4);
        ex.changePriority(9, 150);
        assertEquals((int) ex.removeSmallest(), 1);
        assertEquals((int) ex.removeSmallest(), 17);
        assertEquals((int) ex.removeSmallest(), 9);
        assertEquals((int) ex.removeSmallest(), 12);
        assertEquals((int) ex.removeSmallest(), 13);
        assertEquals((int) ex.removeSmallest(), 11);
        assertEquals((int) ex.removeSmallest(), 19);
        assertEquals((int) ex.removeSmallest(), 14);
        assertEquals((int) ex.removeSmallest(), 15);
        assertEquals((int) ex.removeSmallest(), 20);
        assertEquals((int) ex.removeSmallest(), 18);
        assertEquals((int) ex.removeSmallest(), 8);
        //ex.removeSmallest(); //exception
    }
    @Test
    public void testSpeedMyImplementation() {
        int size = 1000000;
        ArrayList<Integer> randomBox1 = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            randomBox1.add(i);
        }
        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ex.add(randomBox1.remove(rand.nextInt(randomBox1.size())),
                    StdRandom.uniform(0.0, 100000.0));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0
                + " seconds. for My Add");

        long start1 = System.currentTimeMillis();
        ex.contains(999999);
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0
                    + " seconds. for My contains");

        long start2 = System.currentTimeMillis();
        ex.getSmallest();
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0
                + " seconds. for My getSmallest");

        long start3 = System.currentTimeMillis();
        ex.changePriority(40, StdRandom.uniform(0.0, 100000.0));
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0
                + " seconds. for My changePriority");

        long start4 = System.currentTimeMillis();
        ex.removeSmallest();
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end4 - start4) / 1000.0
                + " seconds. for My removeSmallest");
    }

    @Test
    public void testSpeedNaiveImplementation() {
        int size = 1000000;
        ArrayList<Integer> randomBox = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            randomBox.add(i);
        }
        NaiveMinPQ<Integer> badEx = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            badEx.add(randomBox.remove(rand.nextInt(randomBox.size())),
                    StdRandom.uniform(0.0, 100000.0));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0
                + " seconds. for Naive Add");


        long start1 = System.currentTimeMillis();
        badEx.contains(999999);
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0
                + " seconds. for Naive contains");

        long start2 = System.currentTimeMillis();
        badEx.getSmallest();
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0
                + " seconds. for Naive getSmallest");

        long start3 = System.currentTimeMillis();
        badEx.changePriority(40, StdRandom.uniform(0.0, 100000.0));
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0
                + " seconds. for Naive changePriority");

        long start4 = System.currentTimeMillis();
        badEx.removeSmallest();
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end4 - start4) / 1000.0
                + " seconds. for Naive removeSmallest");
    }


    @Test
    public void normalSpeedTest() {
        /** compare add operations */
        NaiveMinPQ<Integer> badEx = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 10000000; i >= 0; i -= 1) {
            badEx.add(i, StdRandom.uniform(0, 100000000));
        } long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0
                + " seconds. for Naive Add");
        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        long start1 = System.currentTimeMillis();
        for (int i = 10000000; i >= 0; i -= 1) {
            ex.add(i, StdRandom.uniform(0, 100000000));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0
                + " seconds. for my Add");
        /** compare contains operations */
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.contains(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0
                + " seconds. for Naive contains");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.contains(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0
                + " seconds. for My contains");
        /** compare getSmallest operations */
        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.getSmallest();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end4 - start4) / 1000.0
                + " seconds. for Naive getSmallest");

        long start5 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.getSmallest();
        }
        long end5 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end5 - start5) / 1000.0
                + " seconds. for my getSmallest");
        /** compare speed test for changePriority operations */
        long start6 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.changePriority(i, StdRandom.uniform(0.0, 100000000.0));
        }
        long end6 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end6 - start6) / 1000.0
                + " seconds. for Naive changePriority");
        long start7 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.changePriority(i, StdRandom.uniform(3.0, 10000000.0));
        }
        long end7 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end7 - start7) / 1000.0
                + " seconds. for my changePriority");
        /** compare removeSmallest operations */
        long start8 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.removeSmallest();
        }
        long end8 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end8 - start8) / 1000.0
                + " seconds. for Naive removeSmallest");
        long start9 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.removeSmallest();
        }
        long end9 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end9 - start9) / 1000.0
                + " seconds. for my removeSmallest");
    }

    @Test
    public void randomSpeedTest() {
        int size = 1000000;
        ArrayList<Integer> randomBox = new ArrayList<>(size);
        ArrayList<Integer> randomBox1 = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            randomBox.add(i);
            randomBox1.add(i);
        }
        /** compare add operations */
        NaiveMinPQ<Integer> badEx = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            badEx.add(randomBox.remove(rand.nextInt(randomBox.size())),
                    StdRandom.uniform(0.0, 1000000));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0
                + " seconds. for Naive Add");

        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ex.add(randomBox1.remove(rand.nextInt(randomBox1.size())),
                    StdRandom.uniform(0.0, 1000000));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0
                + " seconds. for my Add");

        /** compare contains operations */
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.contains(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0
                + " seconds. for Naive contains");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.contains(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0
                + " seconds. for My contains");

        /** compare getSmallest operations */
        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.getSmallest();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end4 - start4) / 1000.0
                + " seconds. for Naive getSmallest");

        long start5 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.getSmallest();
        }
        long end5 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end5 - start5) / 1000.0
                + " seconds. for my getSmallest");
    }
    @Test
    public void randomSpeedTest2() {
        int size = 100000;
        ArrayList<Integer> randomBox = new ArrayList<>(size);
        ArrayList<Integer> randomBox1 = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            randomBox.add(i);
            randomBox1.add(i);
        }
        /** compare add operations */
        NaiveMinPQ<Integer> badEx = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            badEx.add(randomBox.remove(rand.nextInt(randomBox.size())),
                    StdRandom.uniform(0.0, 100000.0));
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0
                + " seconds. for Naive Add");

        ArrayHeapMinPQ<Integer> ex = new ArrayHeapMinPQ<>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ex.add(randomBox1.remove(rand.nextInt(randomBox1.size())),
                    StdRandom.uniform(0.0, 100000.0));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0
                + " seconds. for my Add");

        /** compare speed test for changePriority operations */
        long start6 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.changePriority(i, StdRandom.uniform(3.0, 100000.0));
        }
        long end6 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end6 - start6) / 1000.0
                + " seconds. for Naive changePriority");

        long start7 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.changePriority(i, StdRandom.uniform(3.0, 100000.0));
        }
        long end7 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end7 - start7) / 1000.0
                + " seconds. for my changePriority");

        /** compare removeSmallest operations */
        long start8 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            badEx.removeSmallest();
        }
        long end8 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end8 - start8) / 1000.0
                + " seconds. for Naive removeSmallest");

        long start9 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ex.removeSmallest();
        }
        long end9 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end9 - start9) / 1000.0
                + " seconds. for my removeSmallest");
    }
}
