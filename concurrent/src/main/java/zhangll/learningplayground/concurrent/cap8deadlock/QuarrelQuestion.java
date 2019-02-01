package zhangll.learningplayground.concurrent.cap8deadlock;

import java.util.Random;

class Boy implements Runnable {
    private final String name;
    private Girl girlfriend;
    private final Object heart = new Object();

    Boy(String name) {
        this.name = name;
    }

    /**
     * @return the girlfriend
     */
    public Girl getGirlfriend() {
        return girlfriend;
    }

    /**
     * @param girlfriend the girlfriend to set
     */
    public void setGirlfriend(Girl girlfriend) {
        this.girlfriend = girlfriend;
    }

    @Override
    public void run() {
        while (true) {
            startquarrel();
        }
    }

    //
    public void saysorry() {
        System.out.println("boy[" + name + "]" + "还在打算开始说");
        synchronized (heart) {
            System.out.println("boy saysorry too" + heart.hashCode());
        }
    }

    private void startquarrel() {
        System.out.println("boy[" + name + "] 关闭心扉(heart) ,开始生气");
        synchronized (heart) {
            // 由于男生在生气的时候容易会主动发出向男生发起询问等待最多300秒取询问
            try {
                int waittime = new Random().nextInt(10);
                Thread.sleep(waittime);
                System.out.println("男孩等待" + waittime + "主动试探，但是heart(heart is locking)还没有打开");
                girlfriend.saysorry();
                System.out.println("男孩 说：哈哈");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

class Girl implements Runnable {
    private final String name;
    private Boy boyfriend;
    private final Object heart = new Object();

    Girl(String name) {
        this.name = name;
    }

    /**
     * @return the boyfriend
     */
    public Boy getBoyfriend() {
        return boyfriend;
    }

    /**
     * @param boyfriend the boyfriend to set
     */
    public void setBoyfriend(Boy boyfriend) {
        this.boyfriend = boyfriend;
    }

    @Override
    public void run() {
        while (true) {
            startquarrel();
        }
    }

    public void saysorry() {
        System.out.println("girl[" + name + "]" + "还在打算开始说");
        synchronized (heart) {
            System.out.println("girl saysorry too" + heart.hashCode());
        }
    }

    private void startquarrel() {
        System.out.println("gir[" + name + "]关闭心扉(heart),开始生气");
        synchronized (heart) {
            // 由于女生在生气的时候不容易会主动发出向男生发起询问
            try {
                int waittime = new Random().nextInt(10);
                Thread.sleep(waittime);
                System.out.println("girl等待" + waittime + "主动找男生暗示,但是此时还没有敞开心扉(heart is locking)主动说");
                boyfriend.saysorry();
                System.out.println("girl 说：这次饶了你");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}

public class QuarrelQuestion {
    public static void main(String[] args) {
        Girl jln = new Girl("jln");
        Boy zll = new Boy("zll");
        jln.setBoyfriend(zll);
        zll.setGirlfriend(jln);
        new Thread(jln).start();
        new Thread(zll).start();
    }
}