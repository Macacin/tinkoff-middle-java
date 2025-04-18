package second_task;

public class Worker {

    public void work(int workerId) {
        try {
            System.out.println("worker " + workerId + " occupy production machine ...");
            Thread.sleep(2000);
            System.out.println("worker " + workerId + " release production machine");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
