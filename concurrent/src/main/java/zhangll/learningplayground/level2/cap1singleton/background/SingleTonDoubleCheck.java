package zhangll.learningplayground.level2.cap1singleton.background;

import java.util.stream.IntStream;

public class SingleTonDoubleCheck {
    private static Object obj;

    private SingleTonDoubleCheck() {

    }

    public static Object getInstance() {
        Object objn;
        if (obj == null) {
            synchronized (SingleTonDoubleCheck.class) {
                objn = new Object();
                if (obj == null) {
                    obj = objn;
                }
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0, 10).forEach(i -> new Thread(i + "") {
            @Override
            public void run() {
                System.out.println(
                        "[+" + Thread.currentThread().getName() + "+]" + SingleTonDoubleCheck.getInstance().hashCode());
            }
        }.start());
    }
}