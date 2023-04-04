package bai24;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        FileReader reader = null;
        BufferedReader bReader = null;

        try {
            reader = new FileReader("D:\\SpringBoot\\MultiThreading\\bai24\\student.txt");
            bReader = new BufferedReader(reader);
            String line = null;

            while (sharedData.isAlive()) {
                //sync du lieu tu day
                synchronized (sharedData) {
                    line = bReader.readLine();
                    if(line == null) {
                        sharedData.setAlive(false);
                        break;
                    }
                    System.out.println("T1=========");
                    boolean isValid = Student.checkValidRollNo(line);
                    if(isValid) {
                        Student std = new Student(line);
                        sharedData.getValidRollNumber().add(std);
                        sharedData.setCurrentThread(SharedData.THREAD_2);
                    } else {
                        sharedData.getUnvalidRollNumber().add(line);
                        sharedData.setCurrentThread(SharedData.THREAD_3);
                    }

                    sharedData.notifyAll();
                    try {
                        sharedData.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(bReader != null) {
                try {
                    bReader.close();
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
