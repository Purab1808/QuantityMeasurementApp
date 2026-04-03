package org.apps.quantitymeasurement.service;

import org.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {

    void saveMeasurement(String type, String operation, double value);

    List<QuantityMeasurementEntity> getAllMeasurements();

    int getTotalCount();

    void deleteAll();
}