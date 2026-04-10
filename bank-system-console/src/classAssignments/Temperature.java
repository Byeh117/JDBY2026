package classAssignments;

public abstract class Temperature implements TransferableTemp{
    protected double temperature; // from claude.ai, allows child classes to use variable

    public Temperature() {}
    //Constructor created using claude.ai
    public Temperature(double temperature) {
        this.temperature = temperature;
    }

    public abstract double toFahrenheit();
    public abstract double toCelsius();
    public abstract double toKelvin();

    @Override
    public String transferData() {
        return "F: " + toFahrenheit() + " | C: " + toCelsius() + " | K: " + toKelvin();
    }
    @Override
    public void receiveData(String data) {
        if (data == null || data.isEmpty()) {
            throw new RuntimeException("No data to receive");
        }
        System.out.println("Received Data: " + data);
    }

    public void printAllConversions() { // Prints all temperature variants
        System.out.println("== Temperature Conversions ==");
        System.out.printf("Fahrenheit : %.2f F%n", toFahrenheit());
        System.out.printf("Celsius    : %.2f C%n", toCelsius());
        System.out.printf("Kelvin     : %.2f K%n", toKelvin());
    }

    public double getTemperature() { return temperature; }

}


