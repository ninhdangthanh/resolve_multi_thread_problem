package bai24;

import java.io.*;

public class Thread2 extends Thread{
    SharedData sharedData;

    public Thread2(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        while (sharedData.isAlive()) {
            synchronized (sharedData) {
                sharedData.notifyAll();
                try {
                   while (sharedData.getCurrentThread() != sharedData.THREAD_2 && sharedData.isAlive()) {
                       sharedData.wait();
                   }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int length = sharedData.getValidRollNumber().size();
                if(length >= 0) {
                    Student std = sharedData.getValidRollNumber().get(length - 1);
                    System.out.println("Welcome student has roll no is: " + std.getRollNo());
                    System.out.println("Valid collection has length: " + length);

                    ObjectOutputStream oos = null;
                    File newFolder2  = null;
                    File outputFile2 = null;
                    FileOutputStream fos = null;

                    try {
                        // ghi vao file .dat
                        newFolder2  = new File("bai24");
                        outputFile2 = new File("bai24/" + std.getRollNo() + ".dat");
                        fos = new FileOutputStream(outputFile2);
                        oos = new ObjectOutputStream(fos);

                        oos.writeObject(std);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if(oos != null) {
                            try {
                                oos.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if(fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                } else {
                    System.out.println("Error!!!");
                }
                sharedData.setCurrentThread(SharedData.THREAD_1);
            }
        }
        //ket thuc doc du lieu tu file student.txt
        synchronized(sharedData) {
            sharedData.notifyAll();
        }
    }
}
