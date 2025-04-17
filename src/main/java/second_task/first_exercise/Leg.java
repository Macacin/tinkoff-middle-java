package second_task.first_exercise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

public class Leg implements Runnable {

    private final String name;
    private final Semaphore leftLock;
    private final Semaphore rightLock;

    public Leg(String name, Semaphore mySem, Semaphore nextSem) {
        this.name = name;
        this.leftLock = mySem;
        this.rightLock = nextSem;
    }

    @Override
    public void run() {
        try {
            while (Thread.currentThread().isAlive()) {
                leftLock.acquire();
                System.out.println(name);
                Thread.sleep(1_000);
                rightLock.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Semaphore leftLock = new Semaphore(1);
        Semaphore rightLock = new Semaphore(0);
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new Leg("left", leftLock, rightLock)),
                CompletableFuture.runAsync(new Leg("right", rightLock, leftLock))
        ).join();
    }
}
