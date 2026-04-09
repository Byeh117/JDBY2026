package classAssignments;

public class Kelvin extends Temperature {

    public Kelvin(double temperature) {
        if (temperature < 0) {
            throw new RuntimeException("Kelvin cannot be negative");
        }
        super(temperature);
    }

    @Override
    public double toFahrenheit() {
        return (temperature - 273.15) * 9 / 5 + 32;
    }

    @Override
    public double toCelsius() {
        return temperature - 273.15;
    }

    @Override
    public double toKelvin() {
        return temperature;
    }
}