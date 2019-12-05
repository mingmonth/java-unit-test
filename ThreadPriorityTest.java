class ThreadPriorityTest {
    public static void main(String args[]) {
        ThreadPriorityTestThread1 th1 = new ThreadPriorityTestThread1();
        ThreadPriorityTestThread2 th2 = new ThreadPriorityTestThread2();

        th1.setPriority(10);
        th2.setPriority(1);

        System.out.println("Priority of th(-): " + th1.getPriority());
        System.out.println("Priority of th(|): " + th2.getPriority());        
        th1.start();
        th2.start();
    }
}

class ThreadPriorityTestThread1 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("-");
            try {
                Thread.sleep(10);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadPriorityTestThread2 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("|");
            try {
                Thread.sleep(10);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}