package org.apps.quantitymeasurement.controller;

import org.apps.quantitymeasurement.service.IQuantityMeasurementService;

import java.util.List;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void addMeasurement(String type, String operation, double value) {
        service.saveMeasurement(type, operation, value);
    }

        public List<?> showAll() {
            return service.getAllMeasurements();
        }

    public void showCount() {
        System.out.println("Total: " + service.getTotalCount());
    }

    public void deleteAll() {
        service.deleteAll();
    }
}