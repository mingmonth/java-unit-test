class ThreadGroupTest {
    public static void main(String args[]) throws Exception {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("Group1");
        ThreadGroup group2 = new ThreadGroup("Group2");

        ThreadGroup subGroup = new ThreadGroup(group1, "SubGroup");

        group1.setMaxPriority(3);   // 스레드 그룹 group1의 최대우선순위를 3으로 변경

        Runnable r = new Runnable(){        
            @Override
            public void run() {                
                try {
                    Thread.sleep(1000);
                    System.out.println("running!!!: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(group1, r, "th1").start();
        new Thread(subGroup, r, "th2").start();
        new Thread(group2, r, "th3").start();

        System.out.println(">> List of ThreadGroup: " + main.getName()
                    + ", Active ThreadGroup: " + main.activeGroupCount()
                    + ", Active Thread: " + main.activeCount());
        main.list();
        System.out.println("");
        group1.list();
    }
}