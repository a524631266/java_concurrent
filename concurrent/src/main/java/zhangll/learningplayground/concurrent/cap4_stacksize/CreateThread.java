package zhangll.learningplayground.concurrent.cap4_stacksize;

public class CreateThread {
    private static int size = 0;

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("name" + (size++));
                }
            }).start();
            System.out.println("size:" + i);
        }
    }
}