package org.apps.quantitymeasurement.service;

import org.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

import java.util.List;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveMeasurement(String type, String operation, double value) {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(type, operation, value);

        repository.save(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.getAll();
    }

    @Override
    public int getTotalCount() {
        return repository.getCount();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}