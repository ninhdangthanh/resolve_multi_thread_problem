package baitap_bai15_binhphuongjava;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread2 extends Thread{
    SharedData sharedData;

    public Thread2(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        while (sharedData.checkAvaiable()) {
            synchronized(sharedData) {
                sharedData.notifyAll();
                try {
                    while(sharedData.getIndex() != 2 && sharedData.checkAvaiable()) {
                        sharedData.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Thread2.class.getName()).log(Level.SEVERE, null, ex);
                }

                int rad = sharedData.getRad();
                if(rad % 3 == 0) {
                    rad *=rad;
                    System.out.println("T2 >> " + rad);
                }

                sharedData.setIndex(1);
            }
        }
        System.out.println("T2 Stop");
        synchronized(sharedData) {
            sharedData.notifyAll();
        }
    }
}