package baitap_bai15_binhphuongjava;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread1 extends Thread{
    SharedData sharedData;

    public Thread1(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
        }

        Random random = new Random();

        while(sharedData.checkAvaiable()) {
            synchronized(sharedData) {
                int rad = random.nextInt(100) + 1;
                sharedData.setRad(rad);
                sharedData.plus(rad);
                System.out.println("T1 >> " + rad);
                System.out.println("Sum = " + sharedData.total);

                //thiet lap luong tiep theo dc phep chay
                if(rad % 3 == 0) {
                    sharedData.setIndex(2);
                } else {
                    sharedData.setIndex(3);
                }

                //sync thread
                sharedData.notifyAll();
                try {
                    sharedData.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("T1 Stop");
        synchronized(sharedData) {
            sharedData.notifyAll();
        }
    }
}