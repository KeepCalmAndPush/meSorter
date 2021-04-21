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
public class GnomeSorter extends Sorter {

    public GnomeSorter(SorterDisplayDelegate delegate, int[] array) {
        super(delegate, array);
    }

    public GnomeSorter() {
        super();
    }
    
    public synchronized void sort() {
        int currentElementIndex = 0;

        SorterDisplayDelegate delegate = getDelegate();

        while (currentElementIndex < array.length) {
            if (shouldStop) { break; }
            
            if (array == null) {
                break;
            }

            if (currentElementIndex == 0) {
                currentElementIndex += 1;
            }

            int previousElementIndex = currentElementIndex - 1;

            delegate.selectElementsAtIndices(new int[]{currentElementIndex, previousElementIndex});
            sleep();
            if (shouldStop) { break; }
            
            int currentElement = array[currentElementIndex];
            int previousElement = array[previousElementIndex];
            
            if(currentElement < previousElement)  {
                delegate.showComparisonSucceeded(previousElementIndex, currentElementIndex);
                sleep();
                if (shouldStop) { break; }

                delegate.swapElements(currentElementIndex, previousElementIndex);
                sleep();
                if (shouldStop) { break; }

                ArrayUtils.swap(array, previousElementIndex, currentElementIndex);
                
                currentElementIndex = previousElementIndex;
            }
            else {
                delegate.showComparisonFailed(currentElementIndex, previousElementIndex);
                sleep();
                if (shouldStop) { break; }
                
                currentElementIndex += 1;
            }
        }

        delegate.clearSelection();
    }
}
