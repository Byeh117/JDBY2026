package classAssignments;

public class Celsius extends Temperature {

    public Celsius(double temperature) {
        super(temperature);
    }

    @Override
    public double toFahrenheit() {
        return (temperature * 9 / 5) + 32;
    }

    @Override
    public double toCelsius() {
        return temperature;
    }

    @Override
    public double toKelvin() {
        return temperature + 273.15;
    }
}