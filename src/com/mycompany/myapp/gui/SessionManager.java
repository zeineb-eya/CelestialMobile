/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Cipher
 */
public class SessionManager {
      public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom_utilisateur ; 
    private static String mail_utilisateur; 
    private static String password ;
    private static String prenom_utilisateur;
    private static String sudo_utilisateur;

 
    public static Preferences getPref() {
        return pref;
    }


    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom_utilisateur() {
        return pref.get("nom_utilisateur",nom_utilisateur);
    }

    public static void setNom_utilisateur(String nom_utilisateur) {
         pref.set("nom_utilisateur",nom_utilisateur);
    }

    public static String getMail_utilisateur() {
        return pref.get("mail_utilisateur",mail_utilisateur);
    }

    public static void setMail_utilisateur(String mail_utilisateur) {
         pref.set("mail_utilisateur",mail_utilisateur);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
         pref.set("password",password);
    }

    public static String getPrenom_utilisateur() {
     return pref.get("prenom_utilisateur",prenom_utilisateur);

    }

    public static void setPrenom_utilisateur(String prenom_utilisateur) {
             pref.set("prenom_utilisateur",prenom_utilisateur);

    }

    public static String getSudo_utilisateur() {
     return pref.get("sudo_utilisateur",sudo_utilisateur);
    }

    public static void setSudo_utilisateur(String sudo_utilisateur) {
             pref.set("sudo_utilisateur",sudo_utilisateur);
    }


   
}
