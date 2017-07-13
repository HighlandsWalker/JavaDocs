package ro.teamnet.zth.autowired;

public class Foo {

    public Foo() throws IllegalAccessException {
        AutowiredUtils.handleFieldAutowiring(this);
    }

    @MyAutowired("")
    public A a;

    @MyAutowired("bField")
    public B b;

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

}
