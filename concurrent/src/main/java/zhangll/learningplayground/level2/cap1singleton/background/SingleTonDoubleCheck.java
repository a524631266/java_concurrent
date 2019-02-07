package zhangll.learningplayground.level2.cap1singleton.background;

import java.util.stream.IntStream;

public class SingleTonDoubleCheck {
    // 加volatile关键字保持内存可见性，happend-before
    private static volatile SingleTonDoubleCheck obj;
    public Abc foo;

    class Abc {
        public int age = 1;
    }

    public SingleTonDoubleCheck getObj() {
        return obj;
    }

    private SingleTonDoubleCheck() {
        // 很遗憾的是在获取对象的时候此时有些属性的引用还没有创建好
        // try {
        // Thread.sleep(200);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        foo = new Abc();
    }

    public static SingleTonDoubleCheck getInstance() {
        SingleTonDoubleCheck objn;
        if (obj == null) {
            synchronized (SingleTonDoubleCheck.class) {
                objn = new SingleTonDoubleCheck();
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
                System.out.println("[+" + Thread.currentThread().getName() + "+]:" + "object:"
                        + SingleTonDoubleCheck.getInstance().hashCode() + "---abc:age+"
                        + SingleTonDoubleCheck.getInstance().foo.age);
            }
        }.start());
    }
}