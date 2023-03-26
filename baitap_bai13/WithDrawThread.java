package baitap_bai13;

public class WithDrawThread  extends Thread{
    String name;
    MyBank myBank;

    public WithDrawThread(MyBank myBank, String name) {
        this.myBank = myBank;
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i ++) {
            myBank.withDraw(800, name);
        }
    }
}
