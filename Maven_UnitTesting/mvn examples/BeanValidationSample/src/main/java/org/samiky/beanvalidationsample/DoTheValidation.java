package org.samiky.beanvalidationsample;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import org.hibernate.validator.HibernateValidator;
import org.samiky.beanvalidationsample.entities.ArithmeticOperation;
import org.samiky.beanvalidationsample.entities.Car;
import org.samiky.beanvalidationsample.entities.Gender;
import org.samiky.beanvalidationsample.entities.Human;
import org.samiky.beanvalidationsample.entities.Person;

/**
 *
 * @author misandu
 */
public class DoTheValidation {
    
    public static void main(String[] args) throws NoSuchMethodException {
        
        Validator validator = buildValidator();
        
        ExecutableValidator executableValidator = validator.forExecutables();
        
        Car car = buildCar();
        
        Human human = buildHuman();
        
        Set<ConstraintViolation<Car>> constraintViolationsCar = validator.validate(car);
        printConstraintViolations("Car, bean validation", constraintViolationsCar);
        
        Set<ConstraintViolation<Human>> constraintViolationsHuman = validator.validate(human);
        printConstraintViolations("Human, bean validation", constraintViolationsHuman);
        
        ArithmeticOperation arithmeticOperationObject = new ArithmeticOperation();
        Method arithmeticOperationMethod = ArithmeticOperation.class.getMethod("add", new Class[] {Integer.class, Integer.class});
        Object[] parameterValues = new Object[]{0, null};
        Set<ConstraintViolation<ArithmeticOperation>> constraintViolationsArithmeticOperation = executableValidator.validateParameters(arithmeticOperationObject, arithmeticOperationMethod, parameterValues, Default.class);
        printConstraintViolations("ArithmeticOperation, parameters validation for 'add' method", constraintViolationsArithmeticOperation);
        
        parameterValues = new Object[]{1, 2};
        constraintViolationsArithmeticOperation = executableValidator.validateParameters(arithmeticOperationObject, arithmeticOperationMethod, parameterValues, Default.class);
        printConstraintViolations("ArithmeticOperation, parameters validation for 'add' method", constraintViolationsArithmeticOperation);
        
        constraintViolationsArithmeticOperation = executableValidator.validateReturnValue(arithmeticOperationObject, arithmeticOperationMethod, null, Default.class);
        printConstraintViolations("ArithmeticOperation, return result validation for 'add' method", constraintViolationsArithmeticOperation);
        
        constraintViolationsArithmeticOperation = executableValidator.validateReturnValue(arithmeticOperationObject, arithmeticOperationMethod, 3, Default.class);
        printConstraintViolations("ArithmeticOperation, return result validation for 'add' method", constraintViolationsArithmeticOperation);
        
        arithmeticOperationMethod = ArithmeticOperation.class.getMethod("multiply", new Class[] {Integer.class, Integer.class});
        parameterValues = new Object[]{0, null};
        constraintViolationsArithmeticOperation = executableValidator.validateParameters(arithmeticOperationObject, arithmeticOperationMethod, parameterValues, Default.class);
        printConstraintViolations("ArithmeticOperation, parameters validation for 'multiply' method", constraintViolationsArithmeticOperation);
    }

    private static void printConstraintViolations(String message, Set<?> constraintViolations) {
        System.out.println(String.format("%s: %d constraint violations.", message, constraintViolations.size()));
        
        for (Object constraintViolation : constraintViolations) {
            System.out.println(constraintViolation);
        }
        
        System.out.println("");
    }

    private static Validator buildValidator() {
        return Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
    }

    private static Car buildCar() {
        Car car = new Car();
        car.setManufacturer("Mercedes");
        car.setLicensePlate("B56TRF");
        
        Person driver = new Person();
        driver.setFirstName("Geo");
        driver.setLastName("Deo");
        driver.setGender(Gender.MALE);
        driver.setAge(20);
        
        car.setDriver(driver);
        
        Person passenger = new Person();
        passenger.setFirstName("Tania");
        passenger.setLastName("Budi");
        passenger.setAge(18);
        passenger.setGender(Gender.FEMALE);
        
        List<Person> passengers = new ArrayList<>();
        passengers.add(driver);
        passengers.add(passenger);
        
        car.setPassengers(passengers);
        
        return car;                
    }

    private static Human buildHuman() {
        Human human = new Human();
        human.setAge(19);
        human.setGender(Gender.MALE);
        human.setName("Bullfrog");
        
        return human;
    }

}
