package org.anderes.edu.dojo.rest.persistence;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Damit dieses POJO mittels MOXy in ein JSON serislisiert werden kann,
 * müssen alle Klassenmember einen Getter und Setter haben.
 *
 */
public class WeatherData {

    private Integer id = Integer.valueOf(Integer.MIN_VALUE);
    private double temperature;
    private double atmosphericPressure;
    private double density;
    private int windDirection;
    private double windSpeed;

    public WeatherData() {
        super();
    }

    public WeatherData(Integer id) {
        this.id = id;
    }

    public WeatherData setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public WeatherData setId(Integer id) {
        this.id = id;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public WeatherData setAtmosphericPressure(double atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
        return this;
    }

    public double getDensity() {
        return density;
    }

    public WeatherData setDensity(double density) {
        this.density = density;
        return this;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public WeatherData setWindDirection(int windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public WeatherData setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        WeatherData rhs = (WeatherData) obj;
        return new EqualsBuilder()
                        .append(temperature, rhs.temperature)
                        .append(atmosphericPressure, rhs.atmosphericPressure)
                        .append(density, rhs.density)
                        .append(windDirection, rhs.windDirection)
                        .append(windSpeed, rhs.windSpeed)
                        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                        .append(temperature).append(atmosphericPressure)
                        .append(density).append(windDirection).append(windSpeed).toHashCode();
    }
}
