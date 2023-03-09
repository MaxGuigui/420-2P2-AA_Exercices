package org.calma.pig.exercices.labo8;

import java.time.LocalDate;
import java.util.List;

public class InfosInMemory {

    private String id;
    private String prenom;
    private String nom;
    private LocalDate dateNaissance;
    private String courriel;
    private List<String> listDepartInclus;
    private List<String> listDepartExclus;

    public InfosInMemory(String id,
                         String prenom,
                         String nom,
                         LocalDate dateNaissance,
                         String courriel,
                         List<String> listDepartInclus,
                         List<String> listDepartExclus) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.courriel = courriel;
        this.listDepartInclus = listDepartInclus;
        this.listDepartExclus = listDepartExclus;
    }

    public String getId() {
        return id;
    }

    public String getCourriel() {
        return courriel;
    }

    @Override
    public String toString() {
        return "InfosInMemory :\n" +
                "\tid = " + id + '\n' +
                "\tprenom = " + prenom + '\n' +
                "\tnom = " + nom + '\n' +
                "\tdateNaissance = " + dateNaissance + '\n' +
                "\tcourriel = " + courriel + '\n' +
                "\tlistDepartInclus = " + listDepartInclus + '\n' +
                "\tlistDepartExclus = " + listDepartExclus;
    }
}