package zhangll.learningplayground.level2.cap4observer;

public class OneObserver implements Observer {
    // private final Subject sub;

    @Override
    public void doEvent(int n) {
        System.out.println("OnsObserver receive " + n);
    }

    public OneObserver(Subject sub) {
        // this.sub = sub;
        sub.setObs(this);
    }

}