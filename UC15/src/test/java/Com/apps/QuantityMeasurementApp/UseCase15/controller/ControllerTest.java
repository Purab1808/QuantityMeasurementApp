package Com.apps.QuantityMeasurementApp.UseCase15.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Com.apps.QuantityMeasurementApp.UseCase15.dto.QuantityDTO;
import Com.apps.QuantityMeasurementApp.UseCase15.repo.Repository;
import Com.apps.QuantityMeasurementApp.UseCase15.service.Service;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller(new Service(Repository.getInstance()));
    }

    @Test
    void testControllerCompare() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        assertDoesNotThrow(() -> controller.compare(q1, q2));
    }

    @Test
    void testControllerConvert() {
        QuantityDTO q = new QuantityDTO(1, "KILOGRAM");

        assertDoesNotThrow(() -> controller.convert(q, "GRAM"));
    }

    @Test
    void testControllerAdd() {
        QuantityDTO q1 = new QuantityDTO(1, "LITRE");
        QuantityDTO q2 = new QuantityDTO(1000, "MILLILITRE");

        assertDoesNotThrow(() -> controller.add(q1, q2));
    }
}