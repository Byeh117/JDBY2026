package classAssignments;

public class Fahrenheit extends Temperature{
    public Fahrenheit(double temperature) {
        super(temperature);
    }

    @Override
    public double toFahrenheit() {
        return temperature;
    }

    @Override
    public double toCelsius() {
        return (temperature - 32) * (5/9);
    }

    @Override
    public double toKelvin() {
        return ((temperature - 32) * (5/9)) + 273.15;
    }
}
