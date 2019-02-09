package zhangll.learningplayground.fundation.factory;

public class HighCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new HighEngine();
    }

    @Override
    public Tyre createTyre() {
        return new HighTyre();
    }

}