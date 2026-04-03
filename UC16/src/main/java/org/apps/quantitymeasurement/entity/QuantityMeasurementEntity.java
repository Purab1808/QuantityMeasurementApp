package org.apps.quantitymeasurement.entity;

import java.time.LocalDateTime;

public class QuantityMeasurementEntity {

    private String type;
    private String operation;
    private double value;
    private LocalDateTime time;

    public QuantityMeasurementEntity(String type, String operation, double value) {
        this.type = type;
        this.operation = operation;
        this.value = value;
        this.time = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Type: " + type +
                ", Operation: " + operation +
                ", Value: " + value +
                ", Time: " + time;
    }

    public String getType() { return type; }
    public String getOperation() { return operation; }
    public double getValue() { return value; }
    public LocalDateTime getTime() { return time; }
}