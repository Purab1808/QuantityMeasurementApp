package Com.apps.QuantityMeasurementApp.UseCase15.dto;


public class QuantityDTO {
    public double value;
    public String unit;

    public QuantityDTO(double v, String u) {
        value = v;
        unit = u;
    }
}