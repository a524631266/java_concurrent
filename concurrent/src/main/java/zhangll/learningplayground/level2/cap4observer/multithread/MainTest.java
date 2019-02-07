package zhangll.learningplayground.level2.cap4observer.multithread;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        // ThreadAction t1 = new ThreadAction();
        // new OneObserver(t1);
        new OneObserver(Arrays.asList(1, 2));
    }
}