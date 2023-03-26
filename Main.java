public class Main {
    public static void main(String[] args) {
        Thread t2 = new ThreadTwo("NinhDang");
        t2.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x = 1; x < 100; x ++) {
                    System.out.println("T1 >> " + x);
                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        ThreadThree r = new ThreadThree("thanhninhdang");
        Thread t3 = new Thread(r);
        t3.start();
    }
}
