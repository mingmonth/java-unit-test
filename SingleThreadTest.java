class SingleThreadTest {

    public static long sum1 = 0;
    public static long sum2 = 0;

    public static void main(String args[]) {                

        for(int i = 0; i < 10; i++) {

            long startTime = System.currentTimeMillis();

            for(int j = 0; j < 300; j++) {
                System.out.printf("%s", new String("-"));
            }
            
            long consumed_time = (System.currentTimeMillis() - startTime);
            sum1 = sum1 + consumed_time;

            System.out.printf("\n소요시간1: " + consumed_time);
            System.out.printf("\n");
    
            startTime = System.currentTimeMillis();

            for(int j = 0; j < 300; j++) {
                System.out.printf("%s", new String("|"));
            }

            consumed_time = (System.currentTimeMillis() - startTime);
            sum2 = sum2 + consumed_time;
    
            System.out.printf("\n소요시간2: " + consumed_time);
        }

        System.out.printf("\n총 소요시간1: " + sum1 + ", 평균 소요시간1: " + (sum1/10));
        System.out.printf("\n총 소요시간2: " + sum2 + ", 평균 소요시간2: " + (sum2/10));

    }
}