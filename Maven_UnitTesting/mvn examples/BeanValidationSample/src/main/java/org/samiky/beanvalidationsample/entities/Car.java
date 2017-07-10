package org.samiky.beanvalidationsample.entities;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author misandu
 */
public class Car {
    
    @NotNull(message = "Car manufacturer is mandatory")
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;
    
    @Min(2)
    private int seatCount;
    
    @NotNull(message = "Driver is mandatory")
    @Valid
    private Person driver;
    
    @NotNull
    @Valid
    @Size(min = 1, max = 5)
    private List<Person> passengers;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }
}
