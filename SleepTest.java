class SleepTest {
    public static void main(String args[]) {

        SleepTestThread1 th1 = new SleepTestThread1();
        SleepTestThread2 th2 = new SleepTestThread2();
        th1.start();
        th2.start();

        try {
            th1.sleep(2000);
        } catch(InterruptedException e) {}

        System.out.println("<<Main 종료>>");
    }    
}

class SleepTestThread1 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println("\n<<th1 종료>>");
    }
}

class SleepTestThread2 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println("\n<<th2 종료>>");
    }
}