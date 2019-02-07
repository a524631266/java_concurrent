package zhangll.learningplayground.level2.cap4observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里只做最简单的观察者模式
 * 
 * 1.当subject状态改变之后就会发送信息给观察者
 */
public class Subject {
    private List<Observer> obs = new ArrayList<>();
    private int state;

    /**
     * @return List<Observer> return the obs
     */
    public List<Observer> getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(Observer observer) {
        this.obs.add(observer);
    }

    /**
     * @return int return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        if (this.state != state)
            doEvent(state);
        this.state = state;

    }

    public void doEvent(int n) {
        // 简单地打印事件记录
        obs.stream().forEach((o) -> {
            o.doEvent(n);
        });
    }

}