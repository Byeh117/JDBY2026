package classAssignments;

public abstract class Temperature {
    protected double temperature; // from claude.ai, allows child classes to use variable

    //Constructor created using claude.ai
    public Temperature(double temperature, String unit) {
        this.temperature = temperature;
    }

    public abstract double toFahrenheit();
    public abstract double toCelsius();
    public abstract double toKelvin();

    public void printAllConversions() { // Prints all temperature variants
        System.out.println("== Temperature Conversions ==");
        System.out.printf("Fahrenheit : %.2f F%n", toFahrenheit());
        System.out.printf("Celsius    : %.2f C%n", toCelsius());
        System.out.printf("Kelvin     : %.2f K%n", toKelvin());
    }

    public double getTemperature() { return temperature; }

}


