package second_task.second_exercise;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Account {

    private int cacheBalance;
    private final Lock lock = new ReentrantLock();

    public Account(int cacheBalance) {
        this.cacheBalance = cacheBalance;
    }

    public void addMoney(int money) {
        this.cacheBalance += money;
    }

    public boolean takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return false;
        }

        this.cacheBalance -= money;
        return true;
    }

}
