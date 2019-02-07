package zhangll.learningplayground.level2.cap3validate;

public class ValidateTest {
    public static volatile int INITVALUE = 0;
    public static int MAX = 5;

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                int currentid = INITVALUE;
                while (currentid < MAX) {
                    if (INITVALUE != currentid) {
                        System.out.printf("-----当前线程的currentid[%d]与初始值[%d]不一样\n", currentid, INITVALUE);
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        currentid = INITVALUE;
                    }
                }
            }
        }.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        new Thread() {
            @Override
            public void run() {
                int currentid = INITVALUE;
                while (currentid < MAX) {

                    System.out.printf("当前update线程的初始值[%d]不一样\n", INITVALUE++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    currentid = INITVALUE;
                }
            }
        }.start();
    }
}