package zhangll.learningplayground.fundation.factory;

public interface Tyre {
    void revolve();
}

class LowerTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("lower revolve ");
    }

}

class HighTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("high revolve ");
    }

}