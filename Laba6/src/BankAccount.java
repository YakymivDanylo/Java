import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

class BankAccount {
    private AtomicInteger balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(int initialBalance) {
        this.balance = new AtomicInteger(initialBalance);
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance.addAndGet(amount);
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". New balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance.get() >= amount) {
                balance.addAndGet(-amount);
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". New balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to withdraw due to insufficient funds.");
            }
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        return balance.get();
    }

    public void transfer(BankAccount targetAccount, int amount) {
        boolean success = false;
        while (!success) {
            if (this.lock.tryLock() && targetAccount.lock.tryLock()) {
                try {
                    if (this.balance.get() >= amount) {
                        this.withdraw(amount);
                        targetAccount.deposit(amount);
                        System.out.println(Thread.currentThread().getName() + " transferred " + amount);
                        success = true;
                    }
                } finally {
                    this.lock.unlock();
                    targetAccount.lock.unlock();
                }
            }
        }
    }
}
