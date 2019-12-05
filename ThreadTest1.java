public class ThreadTest1{
    public static void main(String []args){        
        MyThread1 thread1 = new MyThread1();
        
        Runnable r = new MyThread2();
		Thread thread2 = new Thread(r, "THREAD-2");
		
		Runnable r2 = new MyThread2();
		MyThread3 thread3 = new MyThread3(r2);
		
		//thread1.setName("THREAD-1");
        thread1.start();
		thread2.start();
		thread3.run();
    }         
}

class MyThread1 extends Thread {
	MyThread1() {}
	// Thread(String name) 용 생성자
	MyThread1(String name) {super(name);}
	public void run() {
		for(int i = 0; i < 5; i++) {
			// 부모인 Thread의 getName을 호출
			System.out.println(getName());
		}
	}
}

class MyThread2 implements Runnable {
	public void run() {
		for(int i = 0; i < 5; i++) {		
			// Thread클래스의 static 메소드인 currentThread를 통해 getName 호출	 			 
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