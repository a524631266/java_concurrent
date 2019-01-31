package zhangll.learningplayground.concurrent.cap3_design;

@FunctionalInterface
interface CalculateStrage {
    public double runablecal(double salary, double bonus);
}

class TaxCalulate implements CalculateStrage {
    private final double salary;
    private final double bonus;
    private CalculateStrage cs;

    public TaxCalulate(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public TaxCalulate(double salary, double bonus, CalculateStrage cs) {
        this.salary = salary;
        this.bonus = bonus;
        this.cs = cs;
    }

    /**
     * 这个是开始计算的任务，不管怎么计算
     * 
     * @return
     */
    public double startcal() {
        double tax = 0;
        if (this.cs == null) {
            tax = runablecal(salary, bonus);
        } else {
            tax = cs.runablecal(salary, bonus);
        }
        return tax;
    }

    /**
     * 这里是用来调整税率或者可变动的一个入口
     * 
     * @return
     */
    // protected double runablecal() {
    // return salary * 0.1 + bonus * 0.2;
    // }

    @Override
    public double runablecal(double salary, double bonus) {
        return 0;
    }

}

public class TestDesignPattern {
    public static void main(String[] args) {
        // 这是第一种方式
        TaxCalulate ts = new TaxCalulate(10000, 1000) {
            @Override
            public double runablecal(double salary, double bonus) {
                return salary * 0.1 + bonus * 0.2;
            }
        };
        double tax = ts.startcal();
        System.out.println(tax);
        // 第二种方式 计算单元以及分离 后期如果你想该计算方法，比如在spring中可以直接替换就行了
        CalculateStrage cs = new CalculateStrage() {
            @Override
            public double runablecal(double salary, double bonus) {
                return salary * 1 + bonus * 2;
            }
        };
        TaxCalulate tc = new TaxCalulate(10000, 1000, cs);
        double tax2 = tc.startcal();
        System.out.println(tax2);
        // 第三种方法java8 写法
        // 函数式接口因为是只有一个方法的接口即函数式接口，所以可以用java8的新特性来重写方法
        TaxCalulate tc_java8 = new TaxCalulate(10000, 1000, (s, b) -> {
            return s * 100 + b * 19;
        });
        double tax_java8 = tc_java8.startcal();
        System.out.println(tax_java8);
    }
}