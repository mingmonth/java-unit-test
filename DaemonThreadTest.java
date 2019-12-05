class DaemonThreadTest implements Runnable {
    static boolean autoSave = false;

    public static void main(String args[]) {
        Thread th1 = new Thread(new DaemonThreadTest());
        th1.setDaemon(true);
        th1.start();

        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            if(i == 3) {
                autoSave = true;
            }
        }

        System.out.println("프로그램을 종료합니다.");
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}