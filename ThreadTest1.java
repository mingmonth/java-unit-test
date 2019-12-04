public class ThreadTest1{
     public static void main(String []args){        
        MyThread1 thread1 = new MyThread1();
        
        Runnable r = new MyThread2();
		Thread thread2 = new Thread(r);
		
		Runnable r2 = new MyThread2();
		MyThread3 thread3 = new MyThread3(r2);
        
        thread1.start();
		thread2.start();
		thread3.run();
     }         
}

class MyThread1 extends Thread {
	 public void run() {
		 for(int i = 0; i < 5; i++) {
			 System.out.println(Thread.currentThread().getName());
		 }
	 }
}

class MyThread2 implements Runnable {
	 public void run() {
		 for(int i = 0; i < 5; i++) {
			 System.out.println("TEST:" + Thread.currentThread().getName());
		 }
	 }
}

class MyThread3 {
	private Runnable r;

	public MyThread3(Runnable r) {
		this.r = r;
	}

	public void run() {
		if(r != null) {
			r.run();	// Runnable인터페이스를 구현한 인스턴스의 run을 호출
		}
	}
}