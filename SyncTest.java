class SyncTest {
    public static void main(String args[]) {
        Runnable r = new SyncTestThread1(1000);
        //Runnable r2 = new SyncTestThread1(500);
        new Thread(r).start();  // ThreadGroup에 의해 참조되므로 gc대상이 아니다.
        new Thread(r).start();  // ThreadGroup에 의해 참조되므로 gc대상이 아니다.
    }
}

class Account {
    private int balance = 1000; // 외부에서 해당 값을 변경할 수 없도록 접근 제어자 private로 설정

    public int getBalance() {
        return balance;
    }

    // 출금
    public synchronized void withdraw(int money, int sleepTime) {
        if(balance >= money) {
            try {
                Thread.sleep(sleepTime);    // 다른 스레드에게 제어권을 넘기기 위한 코드
            } catch (InterruptedException e) {}
            balance -= money;
        }
    }
}

class SyncTestThread1 implements Runnable {
    Account acc = new Account();

    int sleepTime = 0;

    SyncTestThread1(int sleepTIme) {
        this.sleepTime = sleepTIme;
    }

    public void run() {
        while(acc.getBalance() > 0) {
            //100, 200, 300중의 한 값을 임의로 선택해서 출금(withdraw)
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withdraw(money, sleepTime);
            System.out.println("[" + Thread.currentThread().getName() + "]withdraw: " + money + ",balance: " + acc.getBalance());
        }
    }
}