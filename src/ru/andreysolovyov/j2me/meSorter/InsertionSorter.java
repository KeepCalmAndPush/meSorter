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
public class InsertionSorter extends Sorter {

    public void sort() {
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        sortArrayInIndices(indices);
        delegate.clearSelection();
    }

    protected void sortArrayInIndices(int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            moveToSortedPartElementWithIndex(index, indices);
        }
    }

    private void moveToSortedPartElementWithIndex(int elementToMoveIndex, int[] indices) {
        int previousElementIndex = ArrayUtils.elementLessThan(elementToMoveIndex, indices);
        
        while (previousElementIndex != -1) {
            int currentElementIndex = ArrayUtils.elementGreaterThan(previousElementIndex, indices);

            int elementToMove = array[currentElementIndex];
            int previousElement = array[previousElementIndex];

            if (previousElement > elementToMove) {
                delegate.showComparisonSucceeded(currentElementIndex, previousElementIndex);
                sleep();
                ArrayUtils.swap(array, previousElementIndex, currentElementIndex);

                delegate.swapElements(previousElementIndex, currentElementIndex);
                sleep();

                previousElementIndex = ArrayUtils.elementLessThan(previousElementIndex, indices);
            }
            else {
                delegate.showComparisonFailed(currentElementIndex, previousElementIndex);
                sleep();
                break;
            }
        }
    }

}