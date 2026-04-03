package org.apps.quantitymeasurement;

import org.apps.quantitymeasurement.controller.QuantityMeasurementController;
import org.apps.quantitymeasurement.repository.*;
import org.apps.quantitymeasurement.service.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repo =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.addMeasurement("Length", "ADD", 10);
        controller.addMeasurement("Weight", "COMPARE", 5);


        controller.showAll().forEach(System.out::println);


        System.out.println("Total: " + service.getTotalCount());
    }
}