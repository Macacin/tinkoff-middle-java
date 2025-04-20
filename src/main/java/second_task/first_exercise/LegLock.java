package second_task.first_exercise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LegLock implements Runnable {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition turn = lock.newCondition();
    private static boolean isLeft = true;

    private final String name;

    public LegLock(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                lock.lock();

                if ("left".equals(name) && !isLeft) {
                    turn.await();
                }
                if ("right".equals(name) && isLeft) {
                    turn.await();
                }

                System.out.println(name);
                Thread.sleep(1_000);
                isLeft = !isLeft;
                turn.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new LegLock("left")),
                CompletableFuture.runAsync(new LegLock("right"))
        ).join();
    }
}
