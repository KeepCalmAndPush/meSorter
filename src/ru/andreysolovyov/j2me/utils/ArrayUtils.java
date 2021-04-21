/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.utils;

/**
 *
 * @author Администратор
 */
public class ArrayUtils {
    public static void shuffle(int[] array) {
        int length = array.length;
        for (int j = 0; j < 50 * length; j++) {
                int firstIndex = RandomUtils.nextInt(length);
                int secondIndex = RandomUtils.nextInt(length);
                int tmp = array[firstIndex];
                array[firstIndex] = array[secondIndex];
                array[secondIndex] = tmp;
            }
    }

    public static void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    public static int elementLessThan(int i, int[] indices) {
        int index = -1;
        for (int j = 0; j < indices.length; j++) {
            int k = indices[j];
            if (k < i && k > index) {
                index = k;
            }
        }
        
        return index;
    }

    public static int elementGreaterThan(int i, int[] indices) {
        int index = -1;
        for (int j = 0; j < indices.length; j++) {
            int k = indices[j];
            if (index == -1 && k > i) {
                index = k;
                continue;
            }
            if (k > i && k < index) {
                index = k;
            }
        }

        return index;
    }

    public static int[] makeArray(int start, int finish) {
        int array[] = new int[finish - start + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = start + i;
        }

        return array;
    }
}
