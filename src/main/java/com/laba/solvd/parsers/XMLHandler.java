package com.laba.solvd.parsers;

import com.laba.solvd.model.*;
import com.laba.solvd.model.Package;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {
    private StringBuilder info;

    private Airplane airplane;
    private Airline airline;
    private AirplaneType type;
    private int capacity;
    private Package aPackage;
    private Employee employee;
    private License license;

    private List<Package> packages;
    private List<Employee> employees;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Starting document...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Ending document...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        info = new StringBuilder();
        switch (qName) {
            case "airplane":
                airplane = new Airplane();
                break;
            case "airline":
                airline = new Airline();
                break;
            case "type":
                type = new AirplaneType();
                break;
            case "packages":
                packages = new ArrayList<>();
                break;
            case "package":
                aPackage = new Package();
                break;
            case "employees":
                employees = new ArrayList<>();
                break;
            case "employee":
                employee = new Employee();
                break;
            case "license":
                license = new License();
                break;
            default:
                System.out.println("Element should not exist");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id":
                int id = Integer.parseInt(info.toString().trim());
                if (airplane != null)
                    airplane.setId(id);
                else if (airline != null)
                    airline.setId(id);
                else if (type != null)
                    type.setId(id);
                else if (aPackage != null)
                    aPackage.setId(id);
                else if (employee != null)
                    employee.setId(id);
                else if (license != null)
                    license.setId(id);
                break;
            case "name":
                String name = info.toString().trim();
                if (airline != null)
                    airline.setName(name);
                else if (aPackage != null)
                    aPackage.setName(name);
                break;
            case "code":
                String code = info.toString().trim();
                if (airline != null)
                    airline.setCode(code);
                break;
            case "airline":
                airplane.setAirline(airline);
                airline = null;
                break;
            case "brand":
                String brand = info.toString().trim();
                if (type != null)
                    type.setBrand(brand);
                break;
            case "model":
                String model = info.toString().trim();
                if (type != null)
                    type.setModel(model);
                break;
            case "type":
                airplane.setType(type);
                type = null;
                break;
            case "capacity":
                capacity = Integer.parseInt(info.toString().trim());
                if (airplane != null)
                    airplane.setCapacity(capacity);
                break;
            case "address":
                String address = info.toString().trim();
                if (aPackage != null)
                    aPackage.setAddress(address);
                break;
            case "package":
                packages.add(aPackage);
                aPackage = null;
                break;
            case "firstName":
                String first = info.toString().trim();
                if (employee != null)
                    employee.setFirstName(first);
                break;
            case "lastName":
                String last = info.toString().trim();
                if (employee != null)
                    employee.setLastName(last);
                break;
            case "certificationNo":
                int certNo = Integer.parseInt(info.toString().trim());
                if (license != null)
                    license.setCertificationNo(certNo);
                break;
            case "issued":
                LocalDate issue = LocalDate.parse(info.toString().trim());
                if (license != null)
                    license.setIssued(issue);
                break;
            case "expired":
                LocalDate expire = LocalDate.parse(info.toString().trim());
                if (license != null)
                    license.setExpired(expire);
                break;
            case "license":
                employee.setLicense(license);
                license = null;
                break;
            case "employee":
                employees.add(employee);
                employee = null;
                break;
            case "airplane":
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
                    System.out.println("\tPackage for: " + pack.getName());
                    System.out.println("\tPackage Address: " + pack.getAddress() + "\n");
                });

                System.out.println("Employees: ");
                airplane.getEmployees().stream().forEach(employee -> {
                    System.out.println("\tEmployee ID: " + employee.getId());
                    System.out.println("\tFirst Name: " + employee.getFirstName());
                    System.out.println("\tLast Name: " + employee.getLastName());

                    System.out.println("\tLicense: ");
                    System.out.println("\t\tLicense ID: "+ employee.getLicense().getId());
                    System.out.println("\t\tLicense Certification No.: "+ employee.getLicense().getCertificationNo());
                    System.out.println("\t\tIssued: "+ employee.getLicense().getIssued().toString());
                    System.out.println("\t\tExpired: "+ employee.getLicense().getExpired().toString());
                });
            default:
                System.out.println("Element should not exist");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        info.append(ch, start, length);
    }
}
