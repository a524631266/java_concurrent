package zhangll.learningplayground.level2.cap1singleton.background;

import java.util.stream.IntStream;

public class SingleTonStaticInner {
    // 类创建有三步骤
    // 1. class文件加载
    // 2. 给属性赋值，同时给静态变量设null
    // 3. 给static变量真正赋值
    private SingleTonStaticInner() {

    }

    // 4.主动加载的过错才会创建、懒加载的过程
    private static class innerFuntory {
        public static Object foo = new Object();
    }

    public static Object getInstance() {
        name();
        return innerFuntory.foo;
    }

    public static void name() {
        new SingleTonStaticInner().new InnerNomal().say();
    }

    private class InnerNomal {
        private String name = "!";

        private void say() {
            System.out.println(this.name);
        }
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0, 10).forEach(i -> new Thread(i + "") {
            @Override
            public void run() {
                System.out.println(
                        "[+" + Thread.currentThread().getName() + "+]" + SingleTonStaticInner.getInstance().hashCode());
            }
        }.start());
    }
}