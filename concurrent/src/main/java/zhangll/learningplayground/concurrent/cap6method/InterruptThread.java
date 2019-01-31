package zhangll.learningplayground.concurrent.cap6method;

class InterruptThread {
    public static void main(String[] args) {
        // 1. 由于 run方法内部没有检查到异常，所以即使被打断仍然会运行
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("if interrupt" + this.isInterrupted());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        };
        t.start();
        System.out.println("if interrupt" + t.isInterrupted());
        t.interrupt();
        System.out.println("if interrupt" + t.isInterrupted());
    }
}

class InterruptThread2 {
    public static void main(String[] args) {
        // 1. 由于 run方法内部没有检查到异常，所以即使被打断仍然会运行
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("if interrupt" + this.isInterrupted());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        break;
                    }
                }
            }

        };
        t.start();
        System.out.println("if interrupt" + t.isInterrupted());
        t.interrupt();
        System.out.println("if interrupt" + t.isInterrupted());
    }
}