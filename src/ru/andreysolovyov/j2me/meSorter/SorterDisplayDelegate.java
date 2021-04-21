/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.meSorter;

/**
 *
 * @author Администратор
 */
public interface SorterDisplayDelegate {
    public void selectElementsAtIndices(int[] indices);
    public void clearSelection();
    public void showComparisonSucceeded(int index1, int index2);
    public void showComparisonFailed(int index1, int index2);
    public void swapElements(int index1, int index2);
}
