package baitap_bai15_binhphuongjava;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread3 extends Thread{
    SharedData sharedData;

    public Thread3(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        while (sharedData.checkAvaiable()) {
            synchronized(sharedData) {
                sharedData.notifyAll();
                try {
                    while(sharedData.getIndex() != 3 && sharedData.checkAvaiable()) {
                        sharedData.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Thread3.class.getName()).log(Level.SEVERE, null, ex);
                }

                int rad = sharedData.getRad();
                if(rad % 2 == 0) {
                    if(rad % 4 == 0) {
                        System.out.println("So ngau nhien chia het cho 4");
                    } else {
                        System.out.println("So ngau nhieu ko chia het cho 4");
                    }
                } else {
                    System.out.println("Ko le");
                }
                sharedData.setIndex(1);
            }
        }
        System.out.println("T3 Stop");
        synchronized(sharedData) {
            sharedData.notifyAll();
        }
    }
}