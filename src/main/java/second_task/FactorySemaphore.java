package second_task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class FactorySemaphore {

    private static final int MACHINES = 5;
    private static final int WORKERS = 8;
    private static final Worker machine = new Worker();

    public static void main(String[] args) throws InterruptedException {
        Semaphore machines = new Semaphore(MACHINES);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < WORKERS; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    machines.acquire();
                    machine.work(finalI);
                    machines.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
