import java.util.ArrayList;

class SyncTest4 {
    public static void main(String args[]) {
        Table3 table = new Table3();  // 여러 스레드가 공유하는 객체

        new Thread(new Cook3(table), "COOK1").start();
        new Thread(new Customer3(table, "donut"), "CUST1").start();
        new Thread(new Customer3(table, "burger"), "CUST2").start();
        // new Thread(new Customer3(table, "donut"), "CUST3").start();
        // new Thread(new Customer3(table, "burger"), "CUST4").start();

        try {
            Thread.sleep(5000);  // 0.1초 후에 강제 종료시킨다.
        } catch(InterruptedException e) {}

        System.exit(0);
    }
}

class Customer3 implements Runnable {
    private Table3 table;
    private String food;

    Customer3(Table3 table, String food) {
        this.table = table;
        this.food = food;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {}

            String name = Thread.currentThread().getName();

            if(eatFood()) {
                System.out.println(name + " ate a " + food);
            } else {
                System.out.println(name + " failed to eat.");
            }
        }
    }

    boolean eatFood() { return table.remove(food); }
}

class Cook3 implements Runnable {
    private Table3 table;

    Cook3(Table3 table) { this.table = table; }

    public void run() {
        while(true) {
            // 임의의 요리를 하나 선택해서 table에 추가한다.
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try{
                Thread.sleep(1);
            } catch(InterruptedException e) {}
        }
    }
}

class Table3 {
    String[] dishNames = { "donut", "donut", "burger" };    // donut이 더 자주 나온다.
    final int MAX_FOOD = 6; // 테이블에 놓을 수 있는 최대 음식의 개수

    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dishName) {
        while(dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waitng.[요리를 더 이상 담을 수 없습니다.]");
            try {
                wait(); // 요리사스레드를 기다리게 한다.
                Thread.sleep(500);
            } catch(InterruptedException e) {}
        }
        dishes.add(dishName);
        notify();   // 기다리고 있는 손님스레드를 깨우기 위함
        System.out.println("Dishes: " + dishes.toString());                
    }

    public boolean remove(String dishName) {

        synchronized(this) {
            String name = Thread.currentThread().getName();

            while(dishes.size() == 0) {                
                System.out.println(name + " is waitng...[테이블에 음식이 없습니다.]");                
                try{
                    wait(); // 손님스레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch(InterruptedException e) {}
            }

            while(true) {
                // 지정된 요리와 일치하는 요리를 테이블에서 제거한다.
                for(int i = 0; i < dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify();   // 잠자고 있는 요리사스레드를 깨우기 위함
                        return true;
                    }
                }

                try {
                    System.out.println(name + " is waiting..[원하는 음식이 없습니다.]");
                    wait(); // 원하는 음식이 없는 경우 손님스레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch(InterruptedException e) {}
            }            
        }        
        //return false;
    }
    
    public int dishNum() { return dishNames.length; }
}