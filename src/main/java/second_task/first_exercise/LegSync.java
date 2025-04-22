package second_task.first_exercise;

import java.util.concurrent.CompletableFuture;

public class LegSync implements Runnable {

    private static final Object lock = new Object();
    private static volatile String currentLeg = "left";

    private final String name;


    public LegSync(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            synchronized (lock) {
                try {
                    while (!name.equals(currentLeg)) {
                        lock.wait();
                    }
                    System.out.println(name);
                    Thread.sleep(1_000);
                    currentLeg = "left".equals(currentLeg) ? "right" : "left";
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new LegSync("left")),
                CompletableFuture.runAsync(new LegSync("right"))
        ).join();
    }
}
