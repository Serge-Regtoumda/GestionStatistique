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
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<Employee>();
        List<Employee> employeeProfession = new ArrayList<Employee>();

        Scanner sc = new Scanner(System.in);
        System.out.println("ENTRER LE CHEMIN DU FICHIER ?");
        String filePath = sc.nextLine();
//        System.out.println("Bonjour, " + filePath + " !");


        Service service = new Service();

//        String filePath = "/Users/sergeregtoumda/java-workspace/GestStat/statapp/target/classes/com/agitex/data.csv"; // Remplacez cette valeur par le chemin vers votre fichier

        if (filePath.endsWith(".csv")) {
            try {
                employeeList = service.readCSV(filePath);

                System.out.println("Chargement ok !");
                int choix;

                do{
                    System.out.println("MENU : 0: Quitter le programme || 1. Voir la liste || 2. Calculer le salaire moyen par profession");
                    while (!sc.hasNextInt()) {
                        System.out.println("Vous devez entrer un nombre dans le menu !");
                        sc.next();
                    }
                    choix = sc.nextInt();

                    int menu;
                    while (choix!=0){
                        switch (choix) {
                            case 1:
                                System.out.println("Liste des employees");

                                for(Employee emp: employeeList){
                                    System.out.println(emp.toString());
                                }
                                choix=0;
                                break;
                            case 2:
                                    List<String> professions = new ArrayList<String>();

                                    System.out.println("Liste des professions");
                                    for(Employee emp: employeeList){
                                        if(!professions.contains(emp.getProfession().toLowerCase())){
                                            System.out.println(emp.getProfession());
                                            professions.add(emp.getProfession().toLowerCase());
                                        }
                                    }

                                    String profession;

                                    do{
                                        System.out.println("ENTRER LA PROFESSION : ");
                                        profession = sc.nextLine();
                                    } while (!professions.contains(profession.toLowerCase()));

                                    employeeProfession = service.getEmplyeesByProfession(employeeList, profession);

                                    System.out.println("Salaire moyenne: "+ service.calculeSalaireMoyenne(employeeProfession)+ "K€");
                                    choix=3;
                                break;

                            default:
                                System.out.println("Choix inconnu");
                                break;
                        }
                    }
                } while (choix < 0 || choix > 2);

            } catch (Exception e){
                System.out.println(e);
            }



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
