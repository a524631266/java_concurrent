package zhangll.learningplayground.concurrent.cap10hook;

import java.lang.management.ManagementFactory;

public class RunWillKilledHook {
    public static void main(String[] args) {
        // 这是运行时平台的关闭之前的钩子,注册之后在平台被kill 的时候(-9不能被捕获)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("I will be killed ");
            RunWillKilledHook.realiseSource();
        }));
        while (true) {
            System.out.println("I am working");
            try {
                System.out.println(Thread.currentThread().getId());
                System.out.println(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void realiseSource() {
        try {
            Thread.sleep(20_000);
            System.out.println("Has finished release the source!!");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}