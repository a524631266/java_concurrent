package zhangll.learningplayground.level2.cap5singlethreadedexecution;

public class Client {
    public static void main(String[] args) {
        // 门是共享的资源
        Gate gate = new Gate();
        User u1 = new User("zhangsan", "zzzzz", gate);
        User u2 = new User("lisi", "zllllll", gate);
        User u3 = new User("wangwu", "wwwwwwww", gate);
        u1.start();
        u2.start();
        u3.start();
    }
}