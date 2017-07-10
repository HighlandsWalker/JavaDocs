package org.samiky.beanvalidationsample.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author misandu
 */
public class ArithmeticOperation {
    
    @ValidateOnExecution()
    @NotNull
    @Min(2)
    public Integer add(@NotNull @Min(1) Integer a, @NotNull @Min(1) Integer b) {
        return a + b;
    }
    
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

}
