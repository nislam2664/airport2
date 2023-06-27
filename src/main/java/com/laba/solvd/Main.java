package com.laba.solvd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.solvd.model.Airplane;
import com.laba.solvd.parsers.JSONParser;
import com.laba.solvd.parsers.Parser;
import com.laba.solvd.parsers.StAXParser;
import com.laba.solvd.validator.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
    }
}
