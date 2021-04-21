/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.meSorter;

import ru.andreysolovyov.j2me.utils.ArrayUtils;

/**
 *
 * @author Администратор
 */
public class SelectionSorter extends Sorter {

    public void sort() {
        for (int currentElementIndex = 0; currentElementIndex < array.length; currentElementIndex++) {
            int remainingRangeLength = array.length - currentElementIndex;
            int currentMinElementIndex = minElementIndexInRange(currentElementIndex, remainingRangeLength);

            ArrayUtils.swap(array, currentElementIndex, currentMinElementIndex);
            delegate.swapElements(currentElementIndex, currentMinElementIndex);
            sleep();
        }
        delegate.clearSelection();
    }

    private int minElementIndexInRange(int location, int length) {
        int minElementIndex = location;
        int minElement = array[minElementIndex];

        for (int index = location; index < location + length; index++) {
            int currentElement = array[index];
            if (currentElement < minElement) {
                minElement = currentElement;
                minElementIndex = index;
            }

            delegate.showComparisonSucceeded(index, minElementIndex);
            sleep();
        }

        return minElementIndex;
    }
}
