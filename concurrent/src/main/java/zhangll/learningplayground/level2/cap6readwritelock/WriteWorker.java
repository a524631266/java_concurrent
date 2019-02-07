package zhangll.learningplayground.level2.cap6readwritelock;

class WriteWorker extends Thread {
    private final ConsumerProductor cp;
    private final String placeholder;

    public WriteWorker(ConsumerProductor cp, String placeholder) {
        this.cp = cp;
        this.placeholder = placeholder;
    }

    @Override
    public void run() {
        while (true) {
            cp.write(placeholder);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}