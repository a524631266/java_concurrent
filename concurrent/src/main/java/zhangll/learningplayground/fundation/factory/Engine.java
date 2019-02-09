package zhangll.learningplayground.fundation.factory;

public interface Engine {
    void run();
}

class LowerEngine implements Engine {

    @Override
    public void run() {
        System.out.println("this is lower eng");
    }

}

class HighEngine implements Engine {

    @Override
    public void run() {
        System.out.println("this is high eng");
    }

}