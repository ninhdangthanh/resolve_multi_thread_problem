package baitap_bai14_SinhSoBinhPhuong;

public class ThreadSquare extends Thread {
    SharedData sharedData;

    public ThreadSquare(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
//        try {
//            sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        for(int i = 0; i < 10; i ++) {
            synchronized (sharedData) {
                if(sharedData.getRad() == 0) {
                    try {
                        sharedData.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int rad = this.sharedData.getRad();
                rad *= rad;
                System.out.println("PT: " + rad);
                sharedData.notifyAll();
                try {
                    sharedData.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        synchronized (sharedData) {
            sharedData.notifyAll();
        }
    }
}
