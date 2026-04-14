import classAssignments.MovieList;
import classAssignments.Horror;
import classAssignments.Action;
import classAssignments.Comedy;

import classAssignments.Temperature;
import classAssignments.Fahrenheit;
import classAssignments.Celsius;
import classAssignments.Kelvin;

import classAssignments.Student;

import entities.BankAccount;
import entities.CheckingsAccount;
import entities.SavingsAccount;
import entities.Transferable;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<BankAccount> listOfAccounts = List.of(
//                new SavingsAccount("a6f3h1-6e9k7e", new BigDecimal("450.00"), new BigDecimal("4.55")),
//                new SavingsAccount("0259j243r9j29m320", new BigDecimal("1000.00"), new BigDecimal("6.00")),
//                new CheckingsAccount("vuv023h4f378hf93h093", new BigDecimal("1600.00"), new BigDecimal("15.00"))
//        );
//
//        //System.out.println(listOfAccounts.get(0).equals(listOfAccounts.get(0)));
//        Transferable[] transferables = new Transferable[10];
//        int count = 0;
//        for (BankAccount account : listOfAccounts) {
//            account.applyMonthlyFee();
//            if(account instanceof Transferable transferable) {
//                transferables[count++] = transferable;
//            }
//        }
//
//        listOfAccounts.forEach(System.out::println);
//        for (Transferable transferable : transferables) {
//            System.out.println(transferable);
//        }
//
//        listOfAccounts.forEach(System.out::println);
//        for (Transferable transferable : transferables) {
//            System.out.println(transferable);
//        }
//
//        // For Student class
//        Student student = new Student("John", "Doe");
//
//        student.AddCourse("Math");
//        student.AddCourse("English");
//        student.AddCourse("History");
//        student.AddCourse("Science");
//
//        student.ClassList();
//
//        // For Temperature class
//        Temperature temp1 = new Celsius(100);
//        Temperature temp2 = new Fahrenheit(32);
//        Temperature temp3 = new Kelvin(300);
//
//        temp1.printAllConversions();
//        System.out.println();
//
//        // Transfer data from Celsius to Fahrenheit
//        System.out.println("-- Transferring from Celsius to Fahrenheit --");
//        String data = temp1.transferData();
//        System.out.println("Transferred: " + data);
//        temp2.receiveData(data);
//
//        System.out.println();
//
//        // Transfer data from Kelvin to Celsius
//        System.out.println("-- Transferring from Kelvin to Celsius --");
//        data = temp3.transferData();
//        System.out.println("Transferred: " + data);
//        temp1.receiveData(data);
//
//        // For MovieList class
//        MovieList horror = new Horror("The Shining");
//        MovieList action = new Action("Mad Max: Fury Road");
//        MovieList comedy = new Comedy("Superbad");
//
//        MovieList[] movies = { horror, action, comedy };
//
//        for (MovieList movie : movies) {
//            System.out.println(movie.getMovieInfo());
//            System.out.println();
//        }
    }
}