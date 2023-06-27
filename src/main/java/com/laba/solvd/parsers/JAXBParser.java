package com.laba.solvd.parsers;

import com.laba.solvd.model.Airplane;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser implements Parser {

    @Override
    public Airplane parse(String path) {
        Airplane airplane = null;
        File xmlFile = new File(path);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Airplane.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            airplane = (Airplane) jaxbUnmarshaller.unmarshal(xmlFile);

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
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return airplane;
    }
}
