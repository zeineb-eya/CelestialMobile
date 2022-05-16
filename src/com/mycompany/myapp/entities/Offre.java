/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Comparator;

/**
 *
 * @author HP
 */
public class Offre {
      private int id;
    private String nom_offre,description_offre;
    private double prix_offre,reduction;
    private String date_debut_offre,date_fin_offre;

    public Offre() {
    }

    public Offre(int id, String nom_offre, String description_offre, double prix_offre, double reduction, String date_debut_offre, String date_fin_offre) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.description_offre = description_offre;
        this.prix_offre = prix_offre;
        this.reduction = reduction;
        this.date_debut_offre = date_debut_offre;
        this.date_fin_offre = date_fin_offre;
    }

    public Offre(String nom_offre, String description_offre, double prix_offre, double reduction, String date_debut_offre, String date_fin_offre) {
        this.nom_offre = nom_offre;
        this.description_offre = description_offre;
        this.prix_offre = prix_offre;
        this.reduction = reduction;
        this.date_debut_offre = date_debut_offre;
        this.date_fin_offre = date_fin_offre;
    }

    public Offre(double prix_offre, double reduction) {
        this.prix_offre = prix_offre;
        this.reduction = reduction;
    }

    public Offre(int id, String nom_offre, String description_offre, double prix_offre, double reduction) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.description_offre = description_offre;
        this.prix_offre = prix_offre;
        this.reduction = reduction;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomOffre() {
        return nom_offre;
    }

    public void setNomOffre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public String getDescriptionOffre() {
        return description_offre;
    }

    public void setDescriptionOffre(String description_offre) {
        this.description_offre = description_offre;
    }

    public double getPrixOffre() {
        return prix_offre;
    }

    public void setPrixOffre(double prix_offre) {
        this.prix_offre = prix_offre;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public String getDateDebutOffre() {
        return date_debut_offre;
    }

    public void setDateDebutOffre(String date_debut_offre) {
        this.date_debut_offre = date_debut_offre;
    }

    public String getDateFinOffre() {
        return date_fin_offre;
    }

    public void setDateFinOffre(String date_fin_offre) {
        this.date_fin_offre = date_fin_offre;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", nom_offre=" + nom_offre + ", description_offre=" + description_offre + ", prix_offre=" + prix_offre + ", reduction=" + reduction + ", date_debut_offre=" + date_debut_offre + ", date_fin_offre=" + date_fin_offre + '}';
    }
    
   public static Comparator<Offre> triOffreDateDebutA = new Comparator<Offre>() {
        @Override
        public int compare(Offre o1, Offre o2) {
            return (int) (o1.getDateDebutOffre().toLowerCase().compareTo(o2.getDateDebutOffre().toLowerCase()));
        }
    };
   
    public static Comparator<Offre> triOffreDateFinA = new Comparator<Offre>() {
        @Override
        public int compare(Offre o1, Offre o2) {
            return (int) (o1.getDateFinOffre().toLowerCase().compareTo(o2.getDateFinOffre().toLowerCase()));
        }
    };
    
    public static Comparator<Offre> triOffreNomA = new Comparator<Offre>() {
        @Override
        public int compare(Offre o1, Offre o2) {
            return (int) (o1.getNomOffre().toLowerCase().compareTo(o2.getNomOffre().toLowerCase()));
        }
    };
}


