package baitap_bai14_SinhSoBinhPhuong;

import java.util.Random;

public class ThreadRamdom extends Thread {
    SharedData sharedData;

    public ThreadRamdom(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        Random random = new Random();

        for(int i = 0; i < 10; i ++) {
            synchronized (sharedData) {
                int rad = random.nextInt(100) + 1;
                sharedData.setRad(rad);
                System.out.println("Rad: " + rad);
                sharedData.notifyAll();
                try {
                    sharedData.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (sharedData) {
                sharedData.notifyAll();
            }
        }
    }
}
