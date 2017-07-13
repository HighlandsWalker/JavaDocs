package ro.teamnet.zth.autowired;

public class A implements SpeakingInterface {
    public A() {
        AutowiredUtils.updateMapIntancesByType(this);
    }

    @Override
    public void tellMeWhereYouAre(){
        System.out.println("I AM IN A CLASS");
    }
}
