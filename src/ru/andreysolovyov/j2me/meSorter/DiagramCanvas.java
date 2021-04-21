/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.meSorter;


import java.util.Enumeration;
import ru.andreysolovyov.j2me.utils.ArrayUtils;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import ru.andreysolovyov.j2me.utils.CanvasView;
/**
 *
 * @author Администратор
 */
public class DiagramCanvas extends Canvas implements SorterDisplayDelegate {

    private static final int BG_COLOR = 0x00000000;
    private static final int BAR_DEFAULT_COLOR = 0x000000FF;
    private static final int BAR_SUCCESS_COLOR = 0x0000FF00;
    private static final int BAR_ERROR_COLOR = 0x00FF0000;
    private static final int BAR_SELECTION_COLOR = 0x00FFFF00;
    
    private Vector elements = new Vector();
    private int[] array;
    private Sorter sorter;

    public DiagramCanvas(final Sorter sorter) {
       super();

       this.sorter = sorter;
       
       Command back = new Command("Назад", Command.BACK, 2);
       Command restart = new Command("Заново", Command.CANCEL, 1);

       addCommand(back);
       addCommand(restart);

       scheduleSorting();
    }

    private void scheduleSorting() {
        prepareArray();
        prepareViews();
       
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {
                sorter.setDelegate(DiagramCanvas.this);
                sorter.setArray(array);
                sorter.start();
            }
        };
        timer.schedule(task, 500);
    }

    private void prepareArray() {
        array = new int[sorter.getDesiredNumberOfElements()];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ArrayUtils.shuffle(array);
    }

    private void prepareViews() {
        int contentWidth = getWidth();// - 5 - 5;
        int maxHeight = getHeight() + 12 - 5 - 5;
        int minHeight = maxHeight / 3;
        int delta = 0;
        int elementsCount = array.length;
        if (elementsCount > 0) {
            delta = (maxHeight - minHeight) / elementsCount;
        }
        int viewWidth = contentWidth / (2 * elementsCount + 1);
        int inset = (contentWidth - (viewWidth * (2 * elementsCount + 1))) / 2;
        int originX = viewWidth + inset;
        int originY = getHeight() + 12 - 5 - minHeight;
        
        int currentHeight = minHeight;

        for (int i = 0; i < elementsCount; i++) {
            int element = array[i];
            currentHeight = minHeight + delta * element;
            originY = getHeight() - 5 - minHeight - delta * element;
            CanvasView view = new CanvasView();
            view.setX(originX);
            view.setY(originY);
            view.setWidth(viewWidth);
            view.setHeight(currentHeight);
            view.setBackgroundColor(BAR_DEFAULT_COLOR);

            view.setTag(element);
            
            elements.addElement(view);
            
            originX += 2*viewWidth;
        }
    }

    private CanvasView elementByTag(int tag) {
        Enumeration e = elements.elements();
        while (e.hasMoreElements()) {
            CanvasView c = (CanvasView) e.nextElement();
            if (c.getTag() == tag) {
                return c;
            }
        }

        return null;
    }

    public void drawElements(Graphics g) {
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            CanvasView view = elementByTag(element);

            g.setColor(view.getBackgroundColor());
            
            g.fillRect(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        }
    }

    public void selectElementsAtIndices(int[] indices) {
        clearSelection();
        
        for (int i = 0; i < indices.length; i++) {
            int element = array[indices[i]];
            CanvasView view = elementByTag(element);
            view.setBackgroundColor(BAR_SELECTION_COLOR);
        }

        repaint();
    }

    public void showComparisonSucceeded(int index1, int index2) {
        clearSelection();

        CanvasView element1 = elementByTag(array[index1]);
        CanvasView element2 = elementByTag(array[index2]);

        element1.setBackgroundColor(BAR_SELECTION_COLOR);
        element2.setBackgroundColor(BAR_SUCCESS_COLOR);

        repaint();
    }

    public void showComparisonFailed(int index1, int index2) {
        clearSelection();
        
        CanvasView element1 = elementByTag(array[index1]);
        CanvasView element2 = elementByTag(array[index2]);

        element1.setBackgroundColor(BAR_SELECTION_COLOR);
        element2.setBackgroundColor(BAR_ERROR_COLOR);

        repaint();
    }

    public void swapElements(int index1, int index2) {
        CanvasView element1 = elementByTag(array[index1]);
        CanvasView element2 = elementByTag(array[index2]);

        int tmpX = element1.getX();

        element1.setX(element2.getX());
        element2.setX(tmpX);

        repaint();
    }

    public void clearSelection() {
        Enumeration enumeration = elements.elements();
        while (enumeration.hasMoreElements()) {
            CanvasView view = (CanvasView) enumeration.nextElement();
            view.setBackgroundColor(BAR_DEFAULT_COLOR);
        }
        repaint();
    }
    
    public void paint(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawElements(g);
    }
}
