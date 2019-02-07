package zhangll.learningplayground.level2.cap1singleton;

import java.util.stream.IntStream;

public class EnumSingleTon {
    // 不能让它实例化
    private EnumSingleTon() {

    }

    // 静态工厂
    public static EnumSingleTon getInstance() {
        return Day.SIX.getInstance();
    }

    // 枚举
    enum Day {
        MON, TU, TH, FOUR, FRI, SIX, SENVEN;
        private final EnumSingleTon te;

        private Day() {
            te = new EnumSingleTon();
        }

        public EnumSingleTon getInstance() {
            return te;
        }
    }

    public static void main(String[] args) {
        // System.out.println(Day.FOUR);
        IntStream.rangeClosed(0, 40).forEach((i) -> {
            new Thread("") {
                @Override
                public void run() {
                    System.out.println(EnumSingleTon.getInstance());
                }
            }.start();
        });

    }
}