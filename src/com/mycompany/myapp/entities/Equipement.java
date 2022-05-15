/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Comparator;

/**
 *
 * @author 21628
 */
public class Equipement {
private int id;
private String nom_equipement;
private String etat_equipement;
private String description_equipement;
private String image_equipement;
private String categorie_equipement;


    public Equipement(int id, String nom_equipement, String etat_equipement, String description_equipement, String image_equipement) {
        this.id = id;
        this.nom_equipement = nom_equipement;
        this.etat_equipement = etat_equipement;
        this.description_equipement = description_equipement;
        this.image_equipement = image_equipement;
        
    }

    public Equipement() {
    }

    public Equipement(String nom_equipement, String etat_equipement, String description_equipement, String image_equipement, String categorie_equipement) {
        this.nom_equipement = nom_equipement;
        this.etat_equipement = etat_equipement;
        this.description_equipement = description_equipement;
        this.image_equipement = image_equipement;
        this.categorie_equipement = categorie_equipement;
    }

    public Equipement(int id, String nom_equipement, String etat_equipement, String description_equipement, String image_equipement, String categorie_equipement) {
        this.id = id;
        this.nom_equipement = nom_equipement;
        this.etat_equipement = etat_equipement;
        this.description_equipement = description_equipement;
        this.image_equipement = image_equipement;
        this.categorie_equipement = categorie_equipement;
    }

    public Equipement(String nom_equipement, String etat_equipement, String description_equipement, String image_equipement) {
        this.nom_equipement = nom_equipement;
        this.etat_equipement = etat_equipement;
        this.description_equipement = description_equipement;
        this.image_equipement = image_equipement;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_equipement() {
        return nom_equipement;
    }

    public void setNom_equipement(String nom_equipement) {
        this.nom_equipement = nom_equipement;
    }

    public String getEtat_equipement() {
        return etat_equipement;
    }

    public void setEtat_equipement(String etat_equipement) {
        this.etat_equipement = etat_equipement;
    }

    public String getDescription_equipement() {
        return description_equipement;
    }

    public void setDescription_equipement(String description_equipement) {
        this.description_equipement = description_equipement;
    }

    public String getImage_equipement() {
        return image_equipement;
    }

    public void setImage_equipement(String image_equipement) {
        this.image_equipement = image_equipement;
    }
        public String getCategorie_equipement() {
        return categorie_equipement;
    }

    public void setCategorie_equipement(String categorie_equipement) {
        this.categorie_equipement = categorie_equipement;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", nom_equipement=" + nom_equipement + ", etat_equipement=" + etat_equipement + ", description_equipement=" + description_equipement + ", categorie_equipement=" + categorie_equipement + '}';
    }
public static Comparator<Equipement> statusComparator = new Comparator<Equipement>() {
        @Override
        public int compare(Equipement o1, Equipement o2) {
            return (int) (o1.getEtat_equipement().toLowerCase().compareTo(o2.getEtat_equipement().toLowerCase()));
        }
    };
    

    
}
