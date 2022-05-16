/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
  * @author khawl
 */
public class Commentaire {
    private int id ;
    private String date_commentaire;
    private String msg_commentaire;
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public Commentaire() {
    }

    public Commentaire(String date_commentaire, String msg_commentaire, Post post) {
        this.date_commentaire = date_commentaire;
        this.msg_commentaire = msg_commentaire;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public String getDate_commentaire() {
        return date_commentaire;
    }

    public String getMsg_commentaire() {
        return msg_commentaire;
    }

  

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public void setMsg_commentaire(String msg_commentaire) {
        this.msg_commentaire = msg_commentaire;
    }

    
    
    
    
}
