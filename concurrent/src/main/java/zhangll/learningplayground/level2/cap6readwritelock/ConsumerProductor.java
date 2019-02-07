package zhangll.learningplayground.level2.cap6readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConsumerProductor {
    private List<String> queue = new ArrayList<>();
    private final ReadWriteLock LOCK = new ReadWriteLock();

    public ConsumerProductor(int count) {
        IntStream.range(0, count).forEach(i -> queue.add("*"));
    }

    public void write(String placeholder) {
        try {
            LOCK.writelock();
            IntStream.range(0, queue.size()).forEach(i -> queue.set(i, placeholder));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            LOCK.writeunlock();
        }
    }

    public String read() {
        try {
            LOCK.readlock();
            return this.toString();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "err";
        } finally {
            LOCK.readunlock();
        }
    }

    public static void main(String[] args) {
        System.out.println(new ConsumerProductor(8));
    }

    @Override
    public String toString() {
        StringBuffer ss = new StringBuffer("queue:");
        queue.stream().forEach(i -> {
            ss.append(i);
        });
        return ss.toString();
    }
}