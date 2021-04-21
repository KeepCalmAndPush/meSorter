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
public class BubbleSorter extends Sorter {
    private int lastPermutationIndex = -1;

    public void sort() {
        lastPermutationIndex = -1;
        int surfaceElementIndex = 0;

        while (surfaceElementIndex != array.length - 1) {
            floatLightestElementUpToSurface(surfaceElementIndex);

            int lastIndex = lastPermutationIndex;
            if (lastPermutationIndex != -1) {
                surfaceElementIndex = Math.max(lastIndex, surfaceElementIndex + 1);
                lastPermutationIndex = -1;
            } else {
                break;
            }
        }
        delegate.clearSelection();
    }

    private void floatLightestElementUpToSurface(int surfaceElementIndex) {
        int floatingStartElement = array.length - 1;

        for (int i = floatingStartElement; i > surfaceElementIndex; i--) {
            int element = array[i];
            int elementAbove = array[i - 1];

            if (element < elementAbove) {
                delegate.showComparisonSucceeded(i, i - 1);
                sleep();

                ArrayUtils.swap(array, i - 1, i);
                delegate.swapElements(i - 1, i);
                sleep();
                
                lastPermutationIndex = i;
            }
            else {
                delegate.showComparisonFailed(i, i - 1);
                sleep();
            }
        }
    }

}
