package baitap_bai15_binhphuongjava;

public class Main {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Thread1 t1 = new Thread1(sharedData);
        Thread2 t2 = new Thread2(sharedData);
        Thread3 t3 = new Thread3(sharedData);

        t1.start();
        t2.start();
        t3.start();
    }
}