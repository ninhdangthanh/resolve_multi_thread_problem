package baitap_bai13;

public class MyBank {
    int money;

    public MyBank () {

    }

    public MyBank(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public synchronized void withDraw(int money, String name) {
        if(money <= this.money) {
            System.out.println("So tien can rut: " + money + ", Thread: " + name);
            this.money -= money;
        } else {
            System.out.println("So tien rut vuot qua so tien hien tai " + ", Thread: " + name);
        }
        System.out.println("So tien hien tai: " + this.money);
    }
}
