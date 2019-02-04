package zhangll.learningplayground.fundation.enumtest;

import java.util.stream.IntStream;

public class TestEnum {
    // 不能让它实例化
    private TestEnum() {

    }

    public static TestEnum getInstance() {
        return Day.FOUR.getInstance();
    }

    enum Day {
        MON, TU, TH, FOUR, FRI, SIX, SENVEN;
        private final TestEnum te;

        private Day() {
            te = new TestEnum();
        }

        public TestEnum getInstance() {
            return te;
        }
    }

    public static void main(String[] args) {
        // System.out.println(Day.FOUR);
        IntStream.rangeClosed(0, 40).forEach((i) -> {
            new Thread("") {
                @Override
                public void run() {
                    System.out.println(TestEnum.getInstance());
                }
            }.start();
        });

    }
}

enum Car {
    lamborghini(900), tata(2), audi(50), fiat(15), honda(12);
    private int price;

    Car(int p) {
        price = p;
    }

    int getPrice() {
        return price;
    }
}