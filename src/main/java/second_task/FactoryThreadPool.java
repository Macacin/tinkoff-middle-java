package second_task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FactoryThreadPool {

    private static final int MACHINES = 5;
    private static final int WORKERS = 8;
    private static final Worker machine = new Worker();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MACHINES);

        for (int i = 1; i <= WORKERS; i++) {
            int finalI = i;
            executor.submit(() -> machine.work(finalI));
        }

        executor.shutdown();
    }
}
