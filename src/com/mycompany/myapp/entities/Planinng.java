/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Comparator;

/**
 *
 * @author skanr
 */
public class Planinng {
    private int id;
    private String nom_planning,description_planning,destination_planning;
    private double prix_planning,periode_planning;
    private String dateDebut_planning,dateFin_planning;

    public Planinng() {
    }

    public Planinng(int id, String nom_planning, String description_planning, String destination_planning, double prix_planning, double periode_planning, String dateDebut_planning, String dateFin_planning) {
        this.id = id;
        this.nom_planning = nom_planning;
        this.description_planning = description_planning;
        this.destination_planning = destination_planning;
        this.prix_planning = prix_planning;
        this.periode_planning = periode_planning;
        this.dateDebut_planning = dateDebut_planning;
        this.dateFin_planning = dateFin_planning;
    }

    public Planinng( String nom_planning, String description_planning, String destination_planning, double prix_planning, double periode_planning, String dateDebut_planning, String dateFin_planning) {
       this.nom_planning = nom_planning;
        this.description_planning = description_planning;
        this.destination_planning = destination_planning;
        this.prix_planning = prix_planning;
        this.periode_planning = periode_planning;
        this.dateDebut_planning = dateDebut_planning;
        this.dateFin_planning = dateFin_planning;
    }

    public Planinng(double prix_planning, double periode_planning) {
          this.prix_planning = prix_planning;
        this.periode_planning = periode_planning;
    }

    public Planinng(int id, String nom_planning, String description_planning, String destination_planning, double prix_planning, double periode_planning) {
       this.id = id;
        this.nom_planning = nom_planning;
        this.description_planning = description_planning;
        this.destination_planning = destination_planning;
        this.prix_planning = prix_planning;
        this.periode_planning = periode_planning;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPlaninng() {
        return nom_planning;
    }

    public void setNomPlaninng(String nom_planning) {
        this.nom_planning = nom_planning;
    }

    public String getDescriptionPlaninng() {
        return description_planning;
    }

    public void setDescriptionPlaninng(String description_planning) {
        this.description_planning = description_planning;
    }
    public String getDestinationPlaninng() {
        return destination_planning;
    }

    public void setDestinationPlaninng(String destination_planning) {
        this.destination_planning = destination_planning;
    }

    public double getPrixPlaninng() {
        return prix_planning;
    }

    public void setPrixPlaninng(double prix_planning) {
        this.prix_planning = prix_planning;
    }

    public double getPeriodePlaninng() {
        return periode_planning;
    }

    public void setPeriodePlaninng(double periode_planning) {
        this.periode_planning = periode_planning;
    }

    public String getDateDebutPlaninng() {
        return dateDebut_planning;
    }

    public void setDateDebutPlaninng(String dateDebut_planning) {
        this.dateDebut_planning = dateDebut_planning;
    }

    public String getDateFinPlaninng() {
        return dateFin_planning;
    }

    public void setDateFinPlaninng(String dateFin_planning) {
        this.dateFin_planning = dateFin_planning;
    }
     public static Comparator<Planinng> sortPlaninngdatedebut = new Comparator<Planinng>() {
        @Override
        public int compare(Planinng o1, Planinng o2) {
            return (int) (o1.getDateDebutPlaninng().toLowerCase().compareTo(o2.getDateDebutPlaninng().toLowerCase()));
        }
    };

    @Override
    public String toString() {
        return "Planinng{" + "id=" + id + ", nom_planning=" + nom_planning + ", description_planning=" + description_planning + ",destination_planning=" + destination_planning + ", prix_planning=" + prix_planning + ", periode_planning=" + periode_planning + ", dateDebut_planning=" + dateDebut_planning + ", dateFin_planning=" + dateFin_planning + '}';
    }
    
   
    
    
    
}

