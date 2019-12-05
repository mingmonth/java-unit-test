class MultiThreadTest {
    //static long startTime = 0;
    public static long sum1 = 0;
    public static long sum2 = 0;

    public static void main(String args[]) {

        MultiThreadTestThread1 th1 = new MultiThreadTestThread1();
        th1.start();

        for(int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();

            for(int j = 0; j < 300; j++) {
                System.out.printf("%s", new String("-"));
            }

            long consumed_time = (System.currentTimeMillis() - startTime);
            MultiThreadTest.sum1 = MultiThreadTest.sum1 + consumed_time;

            //System.out.printf("\n소요시간1: " + consumed_time);
            //System.out.printf("\n");
        }

        System.out.printf("\n총 소요시간1: " + MultiThreadTest.sum1 + ", 평균 소요시간1: " + (MultiThreadTest.sum1/10));
        System.out.printf("\n");
    }    
}

class MultiThreadTestThread1 extends Thread {
    public void run() {

        for(int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
        
            for(int j = 0; j < 300; j++) {
                System.out.printf("%s", new String("|"));
            }

            long consumed_time = (System.currentTimeMillis() - startTime);
            MultiThreadTest.sum2 = MultiThreadTest.sum2 + consumed_time;

            //System.out.printf("\n소요시간2: " + consumed_time);
            //System.out.printf("\n");
        }

        System.out.printf("\n총 소요시간2: " + MultiThreadTest.sum2 + ", 평균 소요시간2: " + (MultiThreadTest.sum2/10));
    }
}