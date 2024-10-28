class Client implements Runnable {
    private BankAccount account;
    private BankAccount transferAccount;

    public Client(BankAccount account, BankAccount transferAccount) {
        this.account = account;
        this.transferAccount = transferAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
                account.deposit(100);
                Thread.sleep(100);
                account.withdraw(50);
                Thread.sleep(100);
                account.transfer(transferAccount, 30);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        }
    }
}
