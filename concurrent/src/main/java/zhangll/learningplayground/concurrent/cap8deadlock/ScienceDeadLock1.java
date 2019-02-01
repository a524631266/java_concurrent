package zhangll.learningplayground.concurrent.cap8deadlock;

import java.util.ArrayList;

class OrderedScience implements Runnable {
    private final int id;
    private final ChopStick[] cslist;

    public OrderedScience(int id, ChopStick[] cslist) {
        this.id = id;
        this.cslist = cslist;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    private void eat() {
        // 筷子的原子性所以要对筷子临界处理(有且只有一个人能拿起筷子)
        synchronized (cslist[id]) {// 先拿起左边的筷子
            System.out.println("science" + id + " 拿起左边←的筷子");
            synchronized (cslist[(id + 1) % 6]) { // 圆桌右边的筷子
                System.out.println("science" + id + " 拿起右→边的筷子!");
                try {
                    System.out.println("science" + id + " 开始吃饭!");
                    Thread.sleep(200);
                    System.out.println("science" + id + " 结束吃饭!并放下筷子");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }
}

class ChopStick {
    private final int id;

    public ChopStick(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "stick" + id;
    }
}

public class ScienceDeadLock1 {
    public static void main(String[] args) {
        // 创建5根筷子
        ChopStick[] cslist = new ChopStick[6];
        for (int i = 0; i < 6; i++) {
            cslist[i] = new ChopStick(i);
        }
        // 创建五个科学家，这五个科学家都能够独立运行指定逻辑
        for (int i = 0; i < 6; i++) {
            OrderedScience os = new OrderedScience(i, cslist);
            new Thread(os).start();
        }
    }
}
