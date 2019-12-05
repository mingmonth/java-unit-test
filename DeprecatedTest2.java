class DeprecatedTest2 {    
    public static void main(String args[]) {
        
        DeprecatedTest2Thread1 th1 = new DeprecatedTest2Thread1("*", 5000);
        DeprecatedTest2Thread1 th2 = new DeprecatedTest2Thread1("**", 5000);
        DeprecatedTest2Thread1 th3 = new DeprecatedTest2Thread1("***", 500);
        
        long startTime = System.currentTimeMillis();

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(1500);
            th1.suspend();
            Thread.sleep(2500);
            th2.suspend();
            Thread.sleep(3500);
            th1.resume();
            Thread.sleep(3500);
            th1.stop();
            th2.stop();
            Thread.sleep(2500);
            th3.stop();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }       
        
        long consumed_time = (System.currentTimeMillis() - startTime);
        System.out.printf("\n소요시간: " + consumed_time);
    }
}

class DeprecatedTest2Thread1 implements Runnable {
    volatile boolean suspended = false;
    volatile boolean stopped = false;
    int runningCount = 0;
    int sleepTime = 0;
    Thread th;

    DeprecatedTest2Thread1(String name, int sleepTime) {
        this.th = new Thread(this, name);    // Thread(Runnalbe r, String name)
        this.sleepTime = sleepTime;
    }

    public void run() {
        while(!stopped) {
            if(!suspended) {
                System.out.println(Thread.currentThread().getName() + "Running Count: " + (runningCount++));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }            
        }
        System.out.println(Thread.currentThread().getName() + " - stopped");
    }

    public void suspend() { suspended = true; }
    public void resume() { suspended = false; }
    public void stop() { stopped = true; }
    public void start() { th.start(); }
}