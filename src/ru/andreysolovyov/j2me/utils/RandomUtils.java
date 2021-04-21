package ru.andreysolovyov.j2me.utils;


import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Администратор
 */
public class RandomUtils {
    private static Random random;
    static{
        random = new Random();
    }

    public static synchronized int nextInt(int minInclusive, int maxExclusive) {
        int randomInt = Math.abs(random.nextInt());
        randomInt %= (maxExclusive - minInclusive);
        randomInt += minInclusive;

        return randomInt;
    }

    public static synchronized int nextInt(int maxExclusive) {
        return nextInt(0, maxExclusive);
    }
}
