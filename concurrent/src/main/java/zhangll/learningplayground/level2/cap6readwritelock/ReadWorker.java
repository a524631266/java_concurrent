package zhangll.learningplayground.level2.cap6readwritelock;

class ReadWorker extends Thread {
    private final ConsumerProductor cp;

    public ReadWorker(ConsumerProductor cp) {
        this.cp = cp;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("read[" + Thread.currentThread().getName() + "]::" + cp);
        }
    }
}