package zhangll.learningplayground.level2.cap6readwritelock;

public class TestMain {
    public static void main(String[] args) {
        ConsumerProductor cp = new ConsumerProductor(10);
        new ReadWorker(cp).start();
        new ReadWorker(cp).start();
        new WriteWorker(cp, "a").start();
        new WriteWorker(cp, "b").start();
    }
}