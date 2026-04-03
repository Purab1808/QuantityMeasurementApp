package Com.apps.QuantityMeasurementApp.UseCase15.units;
import Com.apps.QuantityMeasurementApp.UseCase15.common.IMeasurable;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        public double convertToBaseUnit(double v) { return v; }
        public double convertFromBaseUnit(double v) { return v; }
    },

    FAHRENHEIT {
        public double convertToBaseUnit(double v) {
            return (v - 32) * 5.0 / 9.0;
        }
        public double convertFromBaseUnit(double v) {
            return (v * 9 / 5) + 32;
        }
    };

    public boolean supportsArithmetic() {
        return false;
    }

    public void validateOperationSupport(String op) {
        throw new UnsupportedOperationException("Temperature doesn't support " + op);
    }

    public double getConversionFactor() { return 1; }

    public String getUnitName() { return name(); }
}