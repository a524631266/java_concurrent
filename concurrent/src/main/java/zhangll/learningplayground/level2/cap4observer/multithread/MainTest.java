package zhangll.learningplayground.level2.cap4observer.multithread;

public class MainTest {
    public static void main(String[] args) {
        ThreadAction t1 = new ThreadAction();
        new OneObserver(t1);
    }
}