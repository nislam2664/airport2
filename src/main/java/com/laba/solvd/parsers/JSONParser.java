package com.laba.solvd.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.solvd.model.Airplane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JSONParser implements Parser {

    @Override
    public Airplane parse(String jsonPath) {
        Airplane airplane = null;

        try (FileInputStream jsonFis = new FileInputStream(jsonPath);) {
            File jsonFile = new File(jsonPath);
            ObjectMapper objMapper = new ObjectMapper();
            airplane = objMapper.readValue(jsonFile, Airplane.class);

            System.out.println("Airplane ID: " + airplane.getId() + "\n");

            System.out.println("Airline ID: " + airplane.getAirline().getId());
            System.out.println("Airline Name: " + airplane.getAirline().getName());
            System.out.println("Airline Code: " + airplane.getAirline().getCode() + "\n");

            System.out.println("AirplaneType ID: " + airplane.getType().getId());
            System.out.println("AirplaneType Brand: " + airplane.getType().getBrand());
            System.out.println("AirplaneType Model: " + airplane.getType().getModel() + "\n");

            System.out.println("Capacity: " + airplane.getCapacity() + "\n");

            System.out.println("Packages: ");
            airplane.getPackages().stream().forEach(pack -> {
                System.out.println("\tPackage ID: " + pack.getId());
                System.out.println("\tPackage Name: " + pack.getName());
                System.out.println("\tPackage Address: " + pack.getAddress() + "\n");
            });

            System.out.println("Employees: ");
            airplane.getEmployees().stream().forEach(employee -> {
                System.out.println("\tEmployee ID: " + employee.getId());
                System.out.println("\tFirst Name: " + employee.getFirstName());
                System.out.println("\tLast Name: " + employee.getLastName());

                System.out.println("\tLicense: ");
                System.out.println("\t\tLicense ID: "+ employee.getLicense().getId());
                System.out.println("\t\tLicense Certification #: "+ employee.getLicense().getCertificationNo());
                System.out.println("\t\tIssued: "+ employee.getLicense().getIssued().toString());
                System.out.println("\t\tExpired: "+ employee.getLicense().getExpired().toString());
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
