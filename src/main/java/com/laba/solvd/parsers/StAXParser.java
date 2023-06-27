package com.laba.solvd.parsers;

import com.laba.solvd.model.*;
import com.laba.solvd.model.Package;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StAXParser implements Parser {

    @Override
    public Airplane parse(String xmlPath) {
        Airplane airplane = null;

        try (FileInputStream xmlFis = new FileInputStream(xmlPath)) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(xmlFis);

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "airplane":
                            airplane = new Airplane();
                            break;
                        case "id":
                            nextEvent = reader.nextEvent();
                            int data = Integer.parseInt(nextEvent.asCharacters().getData());
                            if (airplane.getId() == null)
                                airplane.setId(data);
                            break;
                        case "airline":
                            airplane.setAirline(parseAirline(reader));
                            break;
                        case "type":
                            airplane.setType(parseType(reader));
                            break;
                        case "capacity":
                            nextEvent = reader.nextEvent();
                            airplane.setCapacity(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "packages":
                            airplane.setPackages(parsePackages(reader));
                            break;
                        case "employees":
                            airplane.setEmployees(parseEmployees(reader));
                            break;
                        default:
                            System.out.println("Element should not exist...");
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("airplane")) {
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
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("XML file was not found...");
        } catch (XMLStreamException e) {
           System.out.println("XML file was unreadable, processing errors occurred");
        }

        return airplane;
    }

    private Airline parseAirline(XMLEventReader reader) {
        Airline airline = new Airline();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "id":
                            nextEvent = reader.nextEvent();
                            airline.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "name":
                            nextEvent = reader.nextEvent();
                            airline.setName(nextEvent.asCharacters().getData());
                            break;
                        case "code":
                            nextEvent = reader.nextEvent();
                            airline.setCode(nextEvent.asCharacters().getData());
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("airline")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return airline;
    }

    private AirplaneType parseType(XMLEventReader reader) {
        AirplaneType type = new AirplaneType();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "id":
                            nextEvent = reader.nextEvent();
                            type.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "brand":
                            nextEvent = reader.nextEvent();
                            type.setBrand(nextEvent.asCharacters().getData());
                            break;
                        case "model":
                            nextEvent = reader.nextEvent();
                            type.setModel(nextEvent.asCharacters().getData());
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("type")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return type;
    }

    private License parseLicense(XMLEventReader reader) {
        License license = new License();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "id":
                            nextEvent = reader.nextEvent();
                            license.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "certificationNo":
                            nextEvent = reader.nextEvent();
                            license.setCertificationNo(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "issued":
                            nextEvent = reader.nextEvent();
                            license.setIssued(LocalDate.parse(nextEvent.asCharacters().getData()));
                            break;
                        case "expired":
                            nextEvent = reader.nextEvent();
                            license.setExpired(LocalDate.parse(nextEvent.asCharacters().getData()));
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("license")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return license;
    }

    private List<Package> parsePackages(XMLEventReader reader) {
        List<Package> packages = new ArrayList<>();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("package"))
                        packages.add(parsePackage(reader));
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("packages"))
                        break;
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return packages;
    }

    private Package parsePackage(XMLEventReader reader) {
        Package pack = new Package();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "id":
                            nextEvent = reader.nextEvent();
                            pack.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "name":
                            nextEvent = reader.nextEvent();
                            pack.setName(nextEvent.asCharacters().getData());
                            break;
                        case "address":
                            nextEvent = reader.nextEvent();
                            pack.setAddress(nextEvent.asCharacters().getData());
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("package")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return pack;
    }

    private List<Employee> parseEmployees(XMLEventReader reader) {
        List<Employee> employees = new ArrayList<>();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("employee"))
                        employees.add(parseEmployee(reader));
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("employees"))
                        break;
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return employees;
    }

    private Employee parseEmployee(XMLEventReader reader) {
        Employee employee = new Employee();

        while (reader.hasNext()) {
            try {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "id":
                            nextEvent = reader.nextEvent();
                            employee.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "firstName":
                            nextEvent = reader.nextEvent();
                            employee.setFirstName(nextEvent.asCharacters().getData());
                            break;
                        case "lastName":
                            nextEvent = reader.nextEvent();
                            employee.setLastName(nextEvent.asCharacters().getData());
                            break;
                        case "license":
                            employee.setLicense(parseLicense(reader));
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equals("employee")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return employee;
    }
}
