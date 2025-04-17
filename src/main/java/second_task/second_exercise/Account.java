package second_task.second_exercise;

import lombok.Getter;

@Getter
public class Account {

    private int cacheBalance;

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
