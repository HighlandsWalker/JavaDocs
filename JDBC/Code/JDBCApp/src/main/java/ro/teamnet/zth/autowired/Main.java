package ro.teamnet.zth.autowired;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        A a = new A();
        B b = new B();

        Foo foo1 = new Foo();
        foo1.getA().tellMeWhereYouAre();
        foo1.getB().tellMeWhereYouAre();

        Foo foo2 = new Foo();
        foo2.getA().tellMeWhereYouAre();
        foo2.getB().tellMeWhereYouAre();
    }
}
