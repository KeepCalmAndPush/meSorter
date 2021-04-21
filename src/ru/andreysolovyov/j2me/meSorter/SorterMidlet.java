/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.j2me.meSorter;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;

/**
 * @author Администратор
 */
public class SorterMidlet extends MIDlet implements CommandListener {

    private static final String CHOICE_ALG_NAME = "Выбором";
    private static final String GNOME_ALG_NAME = "Гномья";
    private static final String INSERTION_ALG_NAME = "Вставками";
    private static final String SHELL_ALG_NAME = "Шелла";
    private static final String BUBBLE_ALG_NAME = "Пузырьком";
    private static final String QUICK_ALG_NAME = "Быстрая";
    private static final String HEAP_ALG_NAME = "Кучей";
    private String[] algNames = {CHOICE_ALG_NAME, GNOME_ALG_NAME, INSERTION_ALG_NAME, SHELL_ALG_NAME, BUBBLE_ALG_NAME, HEAP_ALG_NAME, QUICK_ALG_NAME};

    private Display display;
    private DiagramCanvas diagramCanvas;

    private List algorithmsList;

    public SorterMidlet() {
        super();
        display = Display.getDisplay(this);
        setupList();
    }

    private void setupList() {
        algorithmsList = new List("Алгоритм", List.IMPLICIT);

        for(int i = 0; i < algNames.length; i++) {
            String algName = algNames[i];
            algorithmsList.append(algName, null);
        }
        
        algorithmsList.setSelectedIndex(0, true);

        Command selectionCommand = new Command("Выбор", Command.ITEM, 1);
        Command exitCommand = new Command("Выход", Command.EXIT, 2);
        
        algorithmsList.addCommand(exitCommand);
        algorithmsList.addCommand(selectionCommand);

        algorithmsList.setCommandListener(this);

        display.setCurrent(algorithmsList);
    }

    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (d == algorithmsList) {
            handleListCommand(c);
        } else if (d == diagramCanvas) {
            handleDiagramCommand(c);
        }
    }

    private void handleListCommand(Command c) {
        if (c.getCommandType() == Command.EXIT) {
            notifyDestroyed();
        } else {
            showDiagram();
        }
    }

    private void showDiagram() {
        diagramCanvas = new DiagramCanvas(initSelectedSorter());
        diagramCanvas.setCommandListener(this);

        display.setCurrent(diagramCanvas);
    }

    private Sorter initSelectedSorter() {
        int i = algorithmsList.getSelectedIndex();
        String algName = algNames[i];
        if (algName.equals(CHOICE_ALG_NAME)) {
            return new SelectionSorter();
        }
        if (algName.equals(GNOME_ALG_NAME)) {
            return new GnomeSorter();
        }
        if (algName.equals(INSERTION_ALG_NAME)) {
            return new InsertionSorter();
        }
        if (algName.equals(SHELL_ALG_NAME)) {
            return new ShellSorter();
        }
        if (algName.equals(BUBBLE_ALG_NAME)) {
            return new BubbleSorter();
        }
        if (algName.equals(HEAP_ALG_NAME)) {
            return new HeapSorter();
        }
        if (algName.equals(QUICK_ALG_NAME)) {
            return new QuickSorter();
        }

        return null;
    }

    private void handleDiagramCommand(Command c) {
        if (c.getCommandType() == Command.BACK) {
            display.setCurrent(algorithmsList);
            diagramCanvas = null;
        } else if (c.getCommandType() == Command.CANCEL) {
            showDiagram();
        }
    }
}
