/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.meSorter;

import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Администратор
 */
public class ShellSorter extends InsertionSorter {

    public void sort() {
        DELAY_INTERVAL = 200;

        int distance = array.length / 2;

        while (distance > 0) {
            Vector indexSets = indexSetsForDistance(distance);

            for (Enumeration it = indexSets.elements(); it.hasMoreElements();) {
                Vector set = (Vector) it.nextElement();
                int[] arr = new int[set.size()];
                for (int i = 0; i < arr.length; i++) {
                    int j = ((Integer) set.elementAt(i)).intValue();
                    arr[i] = j;
                }
                super.sortArrayInIndices(arr);
            }

            distance /= 2;
        }
        
        delegate.clearSelection();
    }

    private Vector indexSetsForDistance(int distance) {
        Vector indexSets = new Vector();
        
        for (int i = 0; i < distance; i++) {
            int elementIndex = i;

            Vector indices = new Vector();

            while (elementIndex < array.length) {
                indices.addElement(new Integer(elementIndex));
                elementIndex += distance;
            }
            indexSets.addElement(indices);
        }
        
        return indexSets;
    }

    public int getDesiredNumberOfElements() {
        return 23;
    }
}
