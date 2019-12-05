class JoinTest {
    static long startTime = 0;

    public static void main(String args[]) {
        JoinTestThread1 th1 = new JoinTestThread1();
        JoinTestThread2 th2 = new JoinTestThread2();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

        try {
            th1.join(); // main스레드가 th1의 작업이 끝날 때까지 기다린다.
            th2.join(); // main스레드가 th2의 작업이 끝날 때까지 기다린다.
        } catch(InterruptedException e) {}

        System.out.println("소요시간: " + (System.currentTimeMillis() - JoinTest.startTime));
    }
}

class JoinTestThread1 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print(new String("-"));
        }
    }    
}

class JoinTestThread2 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
    }
}