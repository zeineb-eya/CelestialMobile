/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author khawl
 */
public class Post {
      
     private int id ;
    private String nom;
    private String img_post;
    private String description_post;
   

    public Post() {
    }

    public Post(String nom, String img_post, String description_post) {
        this.nom = nom;
        this.img_post = img_post;
        this.description_post = description_post;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImg_post() {
        return img_post;
    }

    public String getDescription_post() {
        return description_post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImg_post(String img_post) {
        this.img_post = img_post;
    }

    public void setDescription_post(String description_post) {
        this.description_post = description_post;
    }
    
    
    

    
}
