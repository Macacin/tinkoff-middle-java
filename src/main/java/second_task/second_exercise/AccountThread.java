package second_task.second_exercise;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountThread implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            // implement
        }
    }
}
