/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @details     内部静态类的加载
 * @details         2
 * @details         4
 * @details         1
 * @details         template.Main$A@7f39ebdb
 * @details         3
 * @details     跟类加载时候，先加载父类对应部分，没关系。
 */
public class Main {
    static class A {
        static {
            System.out.println(4);
        }
        public A() {
            super();
            System.out.println(1);
        }
        static class B {
            static A a = new A();
        }
        public static A getInstance() {
            return B.a;
        }
    }
    public static void main(String[] args) {
        System.out.println(2);
        System.out.println(A.getInstance());
        System.out.println(3);
    }
}
