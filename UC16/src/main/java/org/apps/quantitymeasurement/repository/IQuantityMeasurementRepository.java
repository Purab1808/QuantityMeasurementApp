package org.apps.quantitymeasurement.repository;

import org.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAll();

    int getCount();

    void deleteAll();
}