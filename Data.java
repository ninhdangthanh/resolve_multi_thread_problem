//notifyAll, notify, wait
//using to certain FirstThread execute before SecondThread

class Data {
    private boolean isReady = false;

    public synchronized void setDataReady() {
        isReady = true;
        notify(); // Notify any waiting threads
    }

    public synchronized void waitForData() throws InterruptedException {
        while (!isReady) {
            wait(); // Wait until data is ready
        }
    }
}

class FirstThread implements Runnable {
    private Data data;

    public FirstThread(Data data) {
        this.data = data;
    }

    public void run() {
        // Do some work to prepare data
        System.out.println("FirstThread");
        data.setDataReady();
    }
}

class SecondThread implements Runnable {
    private Data data;

    public SecondThread(Data data) {
        this.data = data;
    }

    public void run() {
        try {
            data.waitForData(); // Wait for data to be ready
            System.out.println("SecondThread");
            // Do some work with the data
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        Thread firstThread = new Thread(new FirstThread(data));
        Thread secondThread = new Thread(new SecondThread(data));
        secondThread.start();
        firstThread.start();
    }
}