package baitap_bai13;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyBank myBank = new MyBank(1000);

        List<WithDrawThread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            threadList.add(new WithDrawThread(myBank, "T-" + i));
        }

        for(WithDrawThread drawThread: threadList) {
            drawThread.start();
        }
    }
}
