public class ThreadThree implements Runnable{
    String name;
    Thread t;

    public ThreadThree(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int y = 1; y < 100; y ++) {
            System.out.println("Thread 3 ++ " + name + " " + y);
        }
    }
}
