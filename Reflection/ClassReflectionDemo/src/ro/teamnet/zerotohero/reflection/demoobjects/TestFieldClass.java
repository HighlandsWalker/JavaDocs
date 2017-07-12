package ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Gabriel.Tabus on 7/12/2017.
 */
public class TestFieldClass {
    private double myPrivateField = 3;
    public int myField = 2;
    public int myField2 = 3;

    public TestFieldClass(){

    }

    public TestFieldClass(int myField){
        this.myField = myField;
    }

    public void setPrivateField(double myPrivateField){
        this.myPrivateField = myPrivateField;
    }

    public void printSomething(){
        int contor=0;
        //System.out.println("Metoda mostenita yay!");
        Long aux = new Long(1000000);
        for(int i = 0; i < aux; i++){
            contor++;
        }
    }

    public class InClass1 {}
    public class InClass2 {}
}
