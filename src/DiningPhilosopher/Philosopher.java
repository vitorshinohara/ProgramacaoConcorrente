package DiningPhilosopher;

class Philosopher implements Runnable {
    int id = 0;
    Resource fork = null;
    
    public Philosopher(int initId, Resource initr) {
        id = initId;
        fork = initr;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Phil " + id + " thinking");
                Thread.sleep(30);
                System.out.println("Phil " + id + " hungry");
                fork.pickup(id);
                System.out.println("Phil " + id + " eating");
                Thread.sleep(40);
                fork.drop(id);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
