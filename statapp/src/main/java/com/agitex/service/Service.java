package com.agitex.service;

import com.agitex.model.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.lwawt.macosx.CSystemTray;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<Employee> readCSV(String filePath) {

        List<Employee> resultat = new ArrayList<Employee>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1);
                }
                String[] values = line.split(",");
//                for ( String row: values){
//                    System.out.println(row);
//                }
                Employee employee = new Employee();
                employee.setNom(values[0].trim());
                employee.setPrenom(values[1].trim());
                employee.setAge(Long.parseLong(values[2].trim()));
                employee.setProfession(values[3].trim());
                employee.setSalaire(Long.parseLong(values[4].trim()));
                resultat.add(employee);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        // Utilisation de la liste des lignes pour manipuler les données CSV
//        for (Employee row : resultat) {
//            System.out.println("Employee : " + row.toString());
//        }

        return resultat;
    }

    public static List<Employee> readTXT(String filePath) {

        List<String[]> rows = new ArrayList<String[]>();
        List<Employee> resultat = new ArrayList<Employee>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                String[] values = line.split(" ");
//                rows.add(values);
//                System.out.println(values);
                Employee employee = new Employee();
                employee.setNom(values[0].trim());
                employee.setPrenom(values[1].trim());
                employee.setAge(Long.parseLong(values[2].trim()));
                employee.setProfession(values[3].trim());
                employee.setSalaire(Long.parseLong(values[4].trim()));
                resultat.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (String[] row : rows) {
//            for (String value : row) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }
//        for (Employee row : resultat) {
//            System.out.println("Employee : " + row.toString());
//        }
        return resultat;
    }

    public static List<Employee> readXML(String filePath) {

        List<Employee> resultat = new ArrayList<Employee>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("employee"); // Remplacez "element" par le nom de l'élément XML que vous souhaitez récupérer

            // Parcours de tous les éléments XML sélectionnés

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                Employee employee = new Employee();

                employee.setNom(node.getAttributes().getNamedItem("nom").getNodeValue());
                employee.setPrenom(node.getAttributes().getNamedItem("prenom").getNodeValue());
                employee.setAge(Long.parseLong(node.getAttributes().getNamedItem("age").getNodeValue()));
                employee.setProfession(node.getAttributes().getNamedItem("profession").getNodeValue());
                employee.setSalaire(Long.parseLong(node.getAttributes().getNamedItem("salaire").getNodeValue()));

                resultat.add(employee);
            }

            for (Employee row : resultat) {
                System.out.println(row.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultat;
    }

    public static List<Employee> readJSON(String filePath) {

        List<Employee> resultat = new ArrayList<Employee>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            // Parcours de tous les objets JSON dans le tableau
            for (Object obj : jsonArray) {

                JSONObject jsonObject = (JSONObject) obj;

                String nom = (String) jsonObject.get("nom"); // Remplacez "name" par la clé JSON que vous souhaitez récupérer
                String prenom = (String) jsonObject.get("prenom"); // Remplacez "value" par la clé JSON que vous souhaitez récupérer
                Long age = (Long) jsonObject.get("age"); // Remplacez "value" par la clé JSON que vous souhaitez récupérer
                String profession = (String) jsonObject.get("profession"); // Remplacez "value" par la clé JSON que vous souhaitez récupérer
                Long salaire = (Long) jsonObject.get("salaire"); // Remplacez "value" par la clé JSON que vous souhaitez récupérer

                Employee employee = new Employee(nom, prenom, age, profession, salaire);

                resultat.add(employee);
            }

            for (Employee row : resultat) {
                System.out.println(row.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultat;
    }

    public static List<Employee> getEmplyeesByProfession(List<Employee> list, String profession){

        List<Employee> resultat = new ArrayList<Employee>();

        try {
            if(list.isEmpty()){
                System.out.println("La liste est vide");
            } else {
                for(Employee emp: list){
                    if(emp.getProfession().equalsIgnoreCase(profession)){
                        resultat.add(emp);
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return resultat;
    }

    public static float calculeSalaireMoyenne(List<Employee> list){
        long total = 0;

        try {
            if(list.isEmpty()){
                System.out.println("La liste est vide");
            } else {
                for(Employee emp: list){
                    total += emp.getSalaire();
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return total/list.size();
    }


}
