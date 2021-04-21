package ru.andreysolovyov.j2me.meSorter;

public abstract class Sorter {

    protected SorterDisplayDelegate delegate;
    protected int[] array;
    protected Thread workThread;
    protected boolean shouldStop;
    protected int DELAY_INTERVAL = 200;

    public Sorter() {
        super();
    }

    public Sorter(SorterDisplayDelegate delegate, int[] array) {
        this();
        this.delegate = delegate;
        this.array = array;
    }

    public abstract void sort();
    
    /**
     * @return the delegate
     */
    public SorterDisplayDelegate getDelegate() {
        return delegate;
    }

    /**
     * @param delegate the delegate to set
     */
    public void setDelegate(SorterDisplayDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * @return the array
     */
    public int[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(int[] array) {
        this.array = array;
    }

    public void start() {
//        shouldStop = false;
        workThread = new Thread(new Runnable() {
            public void run() {
                sort();
            }
        });
        workThread.start();
    }

    public void stop() {
        shouldStop = true;
    }

    protected void sleep() {
        try {
            Thread.sleep(DELAY_INTERVAL);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public int getDesiredNumberOfElements() {
        return 23;
    }
}

/*
package ru.andreysolovyov.j2me.meSorter;
 */