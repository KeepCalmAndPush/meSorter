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
public class HeapSorter extends Sorter {
    
    public void sort() {
        makeHeap();
        sortHeap();

        delegate.clearSelection();
    }

    private void makeHeap() {
        int length = array.length;

        for (int i = length/2; i >= 0; i--) {
            moveElementUpToTheRoot(i, length-1);
        }
    }

    //Строим бинарное дерево с самым большим элементом в корне
    private void moveElementUpToTheRoot(int currentElementIndex, int remainingLength) {
        int elementToMoveUpIndex = currentElementIndex;
        int currentElement = array[elementToMoveUpIndex];

        while (elementToMoveUpIndex <= remainingLength / 2) {
            int biggestChildElementIndex =
                    biggestChildElementIndexForElementAtIndex(elementToMoveUpIndex, remainingLength);

            int biggestChildElement = array[biggestChildElementIndex];

            if (currentElement >= biggestChildElement) {
                delegate.showComparisonFailed(elementToMoveUpIndex, biggestChildElementIndex);
                sleep();
                break;
            }

            delegate.showComparisonSucceeded(elementToMoveUpIndex, biggestChildElementIndex);
            sleep();

            ArrayUtils.swap(array, elementToMoveUpIndex, biggestChildElementIndex);
            delegate.swapElements(elementToMoveUpIndex, biggestChildElementIndex);
            sleep();
            
            elementToMoveUpIndex = biggestChildElementIndex;
        }
    }

    private int biggestChildElementIndexForElementAtIndex(int currentElementIndex, int remainingLength) {
        delegate.selectElementsAtIndices(new int[]{currentElementIndex});
        sleep();

        int firstChildElementIndex = 2 * currentElementIndex;
        int firstChildElement = array[firstChildElementIndex];

        int secondChildElementIndex = -1;

        int biggestChildElementIndex = firstChildElementIndex;
        int count = 2;

        if (firstChildElementIndex < remainingLength) {
            count++;
            secondChildElementIndex = firstChildElementIndex + 1;
            int secondChildElement = array[secondChildElementIndex];

            if (secondChildElement > firstChildElement) {
                biggestChildElementIndex = secondChildElementIndex;
            }
        }
        
        int[] elementIndicesToSelect = new int[count];
        elementIndicesToSelect[0] = currentElementIndex;
        elementIndicesToSelect[1] = firstChildElementIndex;
        if (secondChildElementIndex != -1) {
            elementIndicesToSelect[2] = secondChildElementIndex;
        }

        delegate.selectElementsAtIndices(elementIndicesToSelect);
        sleep();

        return biggestChildElementIndex;
    }

    private void sortHeap() {
        int length = array.length - 1;

        for (int currentElementIndex = length; currentElementIndex > 0; currentElementIndex--) {
            delegate.showComparisonSucceeded(0, currentElementIndex);
            sleep();

            ArrayUtils.swap(array, 0, currentElementIndex);
            delegate.swapElements(0, currentElementIndex);
            sleep();
            
            moveElementUpToTheRoot(0, currentElementIndex - 1);
        }
    }
}



