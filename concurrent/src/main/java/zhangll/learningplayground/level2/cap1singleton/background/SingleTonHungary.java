package zhangll.learningplayground.level2.cap1singleton.background;

import java.util.Arrays;

public class SingleTonHungary {
    public static Object foo = new Object();

    private SingleTonHungary() {

    }

    public static Object getInstance() {
        return foo;
    }

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream().forEach(i -> new Thread(i + "") {
            @Override
            public void run() {
                System.out.println("i:[" + i + "]" + Thread.currentThread().getName() + "Hungray:"
                        + SingleTonHungary.getInstance().hashCode());
            }
        }.start());
    }
}