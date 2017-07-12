package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobjects.DemoClass;
import ro.teamnet.zerotohero.reflection.demoobjects.TestFieldClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashSet;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {

    public enum myEnum {
        ALEX,
        ANA,
        MARIA;
    }
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //TODO get the class for a String object, and print it
		Class myStringClass = "anaaremere".getClass();
		System.out.println(myStringClass.getSimpleName());
        System.out.println(myStringClass.getCanonicalName());
        System.out.println(myStringClass.getPackage().getName());

        //TODO get the class of an Enum, and print it
        Class myEnumClass = myEnum.ALEX.getClass();
        System.out.println(myEnumClass);
        System.out.println(myEnumClass.getSimpleName());
        System.out.println(myEnumClass.getCanonicalName());

        //TODO get the class of a collection, and print it
        HashSet<Integer> myCollection = new HashSet<Integer>();
        Class myCollClass = myCollection.getClass();
        System.out.println(myCollClass);
        System.out.println(myCollClass.getSimpleName());
        System.out.println(myCollClass.getCanonicalName());

        //TODO get the class of a primitive type, and print it
        int myPrimitive = 1;
        double MyPrimitiveDouble = 2.2;
        
        Class myPrimitiveClass = ((Object)myPrimitive).getClass();
        System.out.println(myPrimitiveClass);
        myPrimitiveClass = double.class;
        System.out.println(myPrimitiveClass);

        //TODO get and print the class for a field of primitive type

        Class myPrimitiveField = Double.class.getField("MAX_VALUE").getClass();
        System.out.println(myPrimitiveField);
        myPrimitiveField = TestFieldClass.class.getField("myField").getClass();
        System.out.println(myPrimitiveField);

        //TODO get and print the class for a primitive type, using the wrapper class
        Field getFieldUssingWrapper = TestFieldClass.class.getField("myField");

        Class fromWrapper = getFieldUssingWrapper.getClass();


        //TODO get the class for a specified class name

        Class fromSpecifiedName = TestFieldClass.class.getName().getClass();
        System.out.println(fromSpecifiedName);
        

        //TODO get the superclass of a class, and print it
        //TODO get the superclass of the superclass above, and print it

        Class superclassClass = DemoClass.class.getSuperclass();
        System.out.println(superclassClass);
        

        //TODO get and print the declared classes within some other class

        Class[] inClasses = TestFieldClass.class.getDeclaredClasses();

        System.out.println(inClasses[0]);
        System.out.println(inClasses[1]);

        //TODO print the number of constructors of a class

        Constructor[] getDeclaredConstructors = TestFieldClass.class.getDeclaredConstructors();

        System.out.println(getDeclaredConstructors[0]);
        System.out.println(getDeclaredConstructors[1]);

        //TODO get and invoke a public constructor of a class

        Constructor myConstructor = TestFieldClass.class.getConstructor(int.class);
        TestFieldClass testClass = (TestFieldClass) myConstructor.newInstance(2);
        System.out.println(testClass.myField);
        

        //TODO get and print the class of one private field
        Field privateField = testClass.getClass().getDeclaredField("myPrivateField");

        privateField.setAccessible(true);
        double aux = (double)privateField.get(testClass);
        System.out.println(aux);
		
		//TODO set and print the value of one private field for an object

        privateField.set(testClass,5.6);
        System.out.println((double)privateField.get(testClass));

        //TODO get and print only the public fields class
        Field[] myPublicFields = testClass.getClass().getFields();

        System.out.println("Fields array size: "+ myPublicFields.length);
        System.out.println(myPublicFields[0].get(testClass));
        System.out.println(myPublicFields[1].get(testClass));

        //TODO get and invoke one public method of a class

        Method myPublicMethod = testClass.getClass().getMethod("setPrivateField", double.class);

        myPublicMethod.invoke(testClass,4.7);

        System.out.println(privateField.getDouble(testClass));
        

        //TODO get and invoke one inherited method of a class
        DemoClass ingeritedTestObject = new DemoClass();

        Method myInheritedMethod = ingeritedTestObject.getClass().getMethod("printSomething");

        myInheritedMethod.invoke(testClass);

        long start = 0;
        long end = 0;
        long result = 0;
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        start = System.currentTimeMillis();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        ingeritedTestObject.printSomething();
        end = System.currentTimeMillis();
        result = end - start;
        System.out.println(result);

		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++)
            myInheritedMethod.invoke(testClass);
        end = System.currentTimeMillis();
        result = end - start;
        System.out.println(result);
		//what do you observe?
		
    }
}
