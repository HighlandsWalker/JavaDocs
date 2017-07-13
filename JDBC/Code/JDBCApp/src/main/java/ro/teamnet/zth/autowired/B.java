package ro.teamnet.zth.autowired;

@MyQualifier("bField")
public class B implements SpeakingInterface {
    public B() {
        AutowiredUtils.updateMapIntancesByQualifier(this);
    }

    @Override
    public void tellMeWhereYouAre() {
        System.out.println("I AM IN B CLASS");
    }
}
