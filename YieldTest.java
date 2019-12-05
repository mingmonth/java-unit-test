class YieldTest {    
    public static void main(String args[]) {
        
        YieldTestThread1 th1 = new YieldTestThread1("*", 5000);
        YieldTestThread1 th2 = new YieldTestThread1("**", 5000);
        YieldTestThread1 th3 = new YieldTestThread1("***", 500);
        
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

class YieldTestThread1 implements Runnable {
    volatile boolean suspended = false;
    volatile boolean stopped = false;
    int runningCount = 0;
    int sleepTime = 0;
    Thread th;

    YieldTestThread1(String name, int sleepTime) {
        this.th = new Thread(this, name);    // Thread(Runnalbe r, String name)
        this.sleepTime = sleepTime;
    }

    public void run() {

        String name = th.getName();

        while(!stopped) {
            if(!suspended) {
                System.out.println(Thread.currentThread().getName() + "Running Count: " + (runningCount++));
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println(name + " - interrupted");
                }
            } else {
                Thread.yield(); // suspened 가 true 이면 양보.
            }        
        }
        System.out.println(Thread.currentThread().getName() + " - stopped");
    }

    public void suspend() { 
        suspended = true; 
        th.interrupt(); // Thread.sleep에 의한 지연 방지를 위해 interrupt 호출
        System.out.println(th.getName() + " - interrupt() by suspend()");
    }

    public void stop() { 
        stopped = true;
        th.interrupt(); // Thread.sleep에 의한 지연 방지를 위해 interrupt 호출
        System.out.println(th.getName() + " - interrupt() by stop()");
    }

    public void resume() { suspended = false; }    
    public void start() { th.start(); }
}