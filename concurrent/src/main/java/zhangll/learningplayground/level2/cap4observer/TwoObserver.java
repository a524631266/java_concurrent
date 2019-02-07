package zhangll.learningplayground.level2.cap4observer;

public class TwoObserver implements Observer {
    // private final Subject sub;

    @Override
    public void doEvent(int n) {
        System.out.println("TwoObserver receive " + n);
    }

    public TwoObserver(Subject sub) {
        // this.sub = sub;
        sub.setObs(this);
    }

}