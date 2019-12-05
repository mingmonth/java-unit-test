import java.util.Iterator;
import java.util.Map;

class DaemonThreadTest2 {
    public static void main(String args[]) throws Exception {
        DaemonThreadTest2Thread1 th1 = new DaemonThreadTest2Thread1("Thread1");
        DaemonThreadTest2Thread2 th2 = new DaemonThreadTest2Thread2("Thread2");
        th1.start();
        th2.start();
    }    
}

class DaemonThreadTest2Thread1 extends Thread {
    DaemonThreadTest2Thread1(String name) {
        super(name);
    }

    public void run() {
        try {
            sleep(5 * 1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DaemonThreadTest2Thread2 extends Thread {
    DaemonThreadTest2Thread2(String name) {
        super(name);
    }

    public void run() {
        Map map = getAllStackTraces();
        Iterator it = map.keySet().iterator();
        
        int x = 0;
        while(it.hasNext()) {
            Object obj = it.next();
            Thread t = (Thread)obj;
            StackTraceElement[] ste = (StackTraceElement[])(map.get(obj));

            System.out.println("["+  ++x + "] name: " + t.getName()
                            + ", group: " + t.getThreadGroup().getName()
                            + ", daemon: " + t.isDaemon());

            for(int i = 0; i < ste.length; i++) {
                System.out.println(ste[i]);
            }

            System.out.println();
        }

    }
}