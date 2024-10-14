public class BankSystem {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);

        Client client1 = new Client(account1, account2);
        Client client2 = new Client(account2, account1);

        Thread clientThread1 = new Thread(client1, "Client 1");
        Thread clientThread2 = new Thread(client2, "Client 2");

        clientThread1.setPriority(Thread.MAX_PRIORITY);
        clientThread2.setPriority(Thread.MIN_PRIORITY);

        clientThread1.start();
        clientThread2.start();

        try {
            Thread.sleep(1000);
            clientThread1.interrupt();
            clientThread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            clientThread1.join();
            clientThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}