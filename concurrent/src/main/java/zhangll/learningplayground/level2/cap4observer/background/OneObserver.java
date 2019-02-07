package zhangll.learningplayground.level2.cap4observer.background;

public class OneObserver implements Observer {
    // private final Subject sub;

    @Override
    public void doEvent(int n) {
        System.out.println("OnsObserver receive " + n);
    }

    public OneObserver(Subject sub) {
        // this.sub = sub;
        // 核心在此,在监听主题的时候,在主题中注册自己
        sub.setObs(this);
    }

}