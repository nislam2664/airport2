package com.laba.solvd;

import com.laba.solvd.model.Airplane;
import com.laba.solvd.parsers.JAXBParser;
import com.laba.solvd.parsers.JSONParser;
import com.laba.solvd.parsers.Parser;
import com.laba.solvd.parsers.StAXParser;
import com.laba.solvd.validator.Validator;

public class Main {
    public static void main(String[] args) {
        String xmlPath = "src/main/resources/airport.xml";
        String jsonPath = "src/main/resources/airport.json";
        String xsdPath = "src/main/resources/airport.xsd";

        Parser parser = null;

        System.out.println("*** XML VALIDATOR ***");
        Validator.validateXMLWithXSD(xmlPath, xsdPath);

        System.out.println("\n*** STAX PARSER ***");
        parser = new StAXParser();
        Airplane staxAirplane = parser.parse(xmlPath);

        System.out.println("\n*** JSON PARSER ***");
        parser = new JSONParser();
        Airplane jsonAirplane = parser.parse(jsonPath);

        System.out.println("\n*** JAXB PARSER ***");
        parser = new JAXBParser();
        Airplane jaxbAirplane = parser.parse(xmlPath);

        System.out.println("\nARE ALL AIRPLANE OBJECTS THE SAME?");
        System.out.println(staxAirplane.equals(jsonAirplane) && jsonAirplane.equals(jaxbAirplane));
    }
}
