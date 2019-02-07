package zhangll.learningplayground.level2.cap4observer;

public class MainClient {
    public static void main(String[] args) {
        Subject sub = new Subject();
        Observer ob = new OneObserver(sub);
        Observer ob2 = new TwoObserver(sub);
        System.out.println("#########################");
        sub.setState(10);
        System.out.println("#########################");
        sub.setState(10);
        System.out.println("#########################");
        sub.setState(11);
    }
}