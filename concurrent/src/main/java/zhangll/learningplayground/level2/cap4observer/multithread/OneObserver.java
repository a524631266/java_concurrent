package zhangll.learningplayground.level2.cap4observer.multithread;

import zhangll.learningplayground.level2.cap4observer.multithread.ThreadAction.ThreadEvent;

public class OneObserver implements ThreadObserver {

    public OneObserver(ThreadAction ta) {
        ta.setTo(this);
    }

    @Override
    public void onEvent(ThreadEvent te) {
        if (te.getTw() != null) {
            System.out.println(te.getT().getName() + "线程的状态:" + te.getTs() + "::::" + te.getTw().getMessage());
        } else {
            System.out.println(te.getT().getName() + "线程的状态:" + te.getTs() + "::::");
        }
    }
}