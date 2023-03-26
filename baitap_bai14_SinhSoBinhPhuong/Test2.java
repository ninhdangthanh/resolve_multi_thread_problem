package baitap_bai14_SinhSoBinhPhuong;

public class Test2 {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        ThreadRamdom threadRamdom = new ThreadRamdom(sharedData);
        ThreadSquare threadSquare = new ThreadSquare(sharedData);

        threadRamdom.start();
        threadSquare.start();
    }
}
