package Com.apps.QuantityMeasurementApp.UseCase15.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Com.apps.QuantityMeasurementApp.UseCase15.dto.QuantityDTO;
import Com.apps.QuantityMeasurementApp.UseCase15.repo.Repository;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setup() {
        service = new Service(Repository.getInstance());
    }

    @Test
    void testCompare_Length() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void testCompare_Weight() {
        QuantityDTO q1 = new QuantityDTO(1, "KILOGRAM");
        QuantityDTO q2 = new QuantityDTO(1000, "GRAM");

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void testConvert_KgToGram() {
        QuantityDTO q = new QuantityDTO(1, "KILOGRAM");

        QuantityDTO result = service.convert(q, "GRAM");

        assertEquals(1000.0, result.value, 0.001);
    }

    @Test
    void testConvert_LitreToMillilitre() {
        QuantityDTO q = new QuantityDTO(1, "LITRE");

        QuantityDTO result = service.convert(q, "MILLILITRE");

        assertEquals(1000.0, result.value, 0.001);
    }

    @Test
    void testAddition_Length() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(2.0, result.value, 0.001);
    }

    @Test
    void testAddition_Volume() {
        QuantityDTO q1 = new QuantityDTO(1, "LITRE");
        QuantityDTO q2 = new QuantityDTO(1000, "MILLILITRE");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(2.0, result.value, 0.001);
    }

    @Test
    void testInvalidUnit() {
        QuantityDTO q = new QuantityDTO(1, "INVALID");

        assertThrows(RuntimeException.class, () -> {
            service.convert(q, "GRAM");
        });
    }

    @Test
    void testTemperature_Add_ShouldFail() {
        QuantityDTO t1 = new QuantityDTO(0, "CELSIUS");
        QuantityDTO t2 = new QuantityDTO(32, "FAHRENHEIT");

        assertThrows(Exception.class, () -> {
            service.add(t1, t2);
        });
    }
}