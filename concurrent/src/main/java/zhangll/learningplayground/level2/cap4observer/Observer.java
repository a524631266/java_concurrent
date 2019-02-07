package zhangll.learningplayground.level2.cap4observer;

public interface Observer {
    // 相应继承接口的继承方法,就是做事件处理
    // 这里就不用事件了,简单用int类型做
    void doEvent(int n);
}