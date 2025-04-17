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
            while (true) {
                boolean from = accountFrom.getLock().tryLock();
                boolean to = accountTo.getLock().tryLock();

                if (from && to) {
                    if (accountFrom.takeOffMoney(money)) {
                        accountTo.addMoney(money);
                        accountFrom.getLock().unlock();
                        accountTo.getLock().unlock();
                        break;
                    }
                }

                if (from) {
                    accountFrom.getLock().unlock();
                }
                if (to) {
                    accountTo.getLock().unlock();
                }
            }
        }
    }
}
