package zhangll.learningplayground.level2.cap4observer.multithread;

public interface ThreadObserver {
    void onEvent(ThreadAction.ThreadEvent te);
}