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
public class QuickSorter extends Sorter {

    public int getDesiredNumberOfElements() {
        return 23;
    }

    public void sort() {
        quickSort(0, array.length - 1);
        delegate.clearSelection();
    }

    public void quickSort(int left, int right) {
        int i = left, j = right;
        int pivotIndex = (left + right) / 2;
        int pivot = array[pivotIndex];

        delegate.selectElementsAtIndices(ArrayUtils.makeArray(left, right));
        sleep();
        while (i <= j)  {
            while (array[i] < pivot) {
                i += 1;
            }
            while (array[j] > pivot) {
                j -= 1;
            }

            if (i <= j) {
                delegate.showComparisonSucceeded(i, j);
                sleep();

                ArrayUtils.swap(array, i, j);
                delegate.swapElements(i, j);
                sleep();
                
                i += 1;
                j -= 1;
            }
        }

        if (left < j) {
            quickSort(left, j);
        }
        if (i < right) {
            quickSort(i, right);
        }
    }
}