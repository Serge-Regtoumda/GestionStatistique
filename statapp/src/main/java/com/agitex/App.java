package com.agitex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.agitex.model.Employee;
import com.agitex.service.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<Employee>();
        List<Employee> employeeProfession = new ArrayList<Employee>();

        Service service = new Service();

        String filePath = "/Users/sergeregtoumda/java-workspace/GestStat/statapp/target/classes/com/agitex/data.csv"; // Remplacez cette valeur par le chemin vers votre fichier

        if (filePath.endsWith(".csv")) {
            employeeList = service.readCSV(filePath);
            employeeProfession = service.getEmplyeesByProfession(employeeList, "boulanger");
            System.out.println("Salaire moyenne: "+ service.calculeSalaireMoyenne(employeeProfession)+ "K€");
        } else if (filePath.endsWith(".txt")) {
            employeeList = service.readTXT(filePath);
            employeeProfession = service.getEmplyeesByProfession(employeeList, "informaticien");
            System.out.println("Salaire moyenne: "+ service.calculeSalaireMoyenne(employeeProfession)+ "K€");
        } else if (filePath.endsWith(".xml")) {
            employeeList = service.readXML(filePath);
            employeeProfession = service.getEmplyeesByProfession(employeeList, "comptable");
            System.out.println("Salaire moyenne: "+ service.calculeSalaireMoyenne(employeeProfession)+ "K€");
        } else if (filePath.endsWith(".json")) {
            employeeList = service.readJSON(filePath);
            employeeProfession = service.getEmplyeesByProfession(employeeList, "policier");
            System.out.println("Salaire moyenne: "+ service.calculeSalaireMoyenne(employeeProfession) + "K€");
        } else {
            System.out.println("Format de fichier non pris en charge.");
        }
    }


}
