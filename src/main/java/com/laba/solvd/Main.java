package com.laba.solvd;

import com.laba.solvd.model.Airplane;
import com.laba.solvd.parsers.StAXParser;
import com.laba.solvd.validator.Validator;

public class Main {
    public static void main(String[] args) {
        String xmlPath = "src/main/resources/airport.xml";
        String jsonPath = "src/main/resources/airport.json";
        String xsdPath = "src/main/resources/airport.xsd";

        System.out.println("*** XML VALIDATOR ***");
        Validator.validateXMLWithXSD(xmlPath, xsdPath);

        System.out.println("\n*** STAX PARSER ***");
        StAXParser staxParser = new StAXParser();
        Airplane staxAirplane = staxParser.parse(xmlPath);


    }
}
