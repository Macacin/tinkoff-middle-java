package second_task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FactoryThreadPool {

    private static final int MACHINES = 5;
    private static final int WORKERS = 8;
    private static final Worker worker = new Worker();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MACHINES);

        for (int i = 0; i <= WORKERS; i++) {
            int finalI = i;
            executor.submit(() -> worker.work(finalI));
        }

        executor.shutdown();
    }
}
