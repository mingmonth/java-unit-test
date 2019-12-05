class ThreadException {
    public static void main(String args[]) throws Exception {
        MyThread t1 = new MyThread();
        t1.start();
    }
}

class MyThread extends Thread {
    public void run() {
        throwException();
    }

    public void throwException() {
        try{
            throw new Exception();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}