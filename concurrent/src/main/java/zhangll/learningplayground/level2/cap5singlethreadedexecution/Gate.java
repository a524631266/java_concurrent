package zhangll.learningplayground.level2.cap5singlethreadedexecution;

public class Gate {
    private int count = 0;
    private String name;
    private String Address;

    public void pass(String name, String Address) {
        this.count++;
        this.name = name;
        this.Address = Address;
        vertify();
    }

    private void vertify() {
        if (this.name.charAt(0) != this.Address.charAt(0)) {
            System.out.println("××××××××××Block×××××××" + toString());
        }
    }

    @Override
    public String toString() {
        return "[id:" + count + "],[name:" + name + "],[address" + Address + "]";
    }
}