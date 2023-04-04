package bai24;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Thread3 extends Thread {
    SharedData sharedData;

    public Thread3 (SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        FileOutputStream fos = null;
        File newFolder3  = null;

        try {
            newFolder3  = new File("bai24/unvalid.txt");
            fos = new FileOutputStream(newFolder3);

           while(sharedData.isAlive()) {
               synchronized (sharedData) {
                   sharedData.notifyAll();
                   while (sharedData.getCurrentThread() != SharedData.THREAD_3 && sharedData.isAlive()) {
                       sharedData.wait();
                   }

                   int length = sharedData.getUnvalidRollNumber().size();
                   String rollNo = sharedData.getUnvalidRollNumber().get(length - 1);

                   System.out.println("Unvalid roll number: " + rollNo);

                   //ghi unvalid roll number vao file
                   rollNo += "\n";
                   byte[] b = rollNo.getBytes();
                   fos.write(b);

                   //chuyen thread
                   sharedData.setCurrentThread(SharedData.THREAD_1);
               }
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //ket thuc doc du lieu tu file student.txt
        synchronized(sharedData) {
            sharedData.notifyAll();
        }
    }
}
