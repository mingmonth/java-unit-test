import javax.swing.JOptionPane;

class InterruptTest {
    public static void main(String args[]) throws Exception{
        InterruptTestThread1 th1 = new InterruptTestThread1();
        th1.start();
        
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        
        th1.interrupt();    // interrupted상태가 true가 된다.
        System.out.println("THREAD: " + Thread.currentThread().getName() + ", isInterrupted(): " + th1.isInterrupted());
    }
}

class InterruptTestThread1 extends Thread {
    public void run() {
        int i = 10;

        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for(long x = 0; x < 2500000000L; x++);    // 시간 지연
            // try {
            //     Thread.sleep(1000);
            // } catch(Exception e) {
            //     //e.printStackTrace();
            //     this.interrupt();
            //     System.out.println("THREAD: " + Thread.currentThread().getName() + ", isInterrupted(): " + this.isInterrupted());
            // }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}