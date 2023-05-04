package com.agitex.model;

public class Employee {
    private String nom;
    private String prenom;
    private Long age;
    private String profession ;
    private Long salaire;

    public Employee(String nom, String prenom, Long age, String profession, Long salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.profession = profession;
        this.salaire = salaire;
    }
    public Employee() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getSalaire() {
        return salaire;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}
