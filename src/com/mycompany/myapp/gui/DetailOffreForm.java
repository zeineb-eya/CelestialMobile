/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.ServiceOffre;

/**
 *
 * @author HP
 */
public class DetailOffreForm extends Form {
  Form current;
     ServiceOffre ServiceOffre = new ServiceOffre();
    
     DetailOffreForm(Offre o) {
                 
        current = this;
        setTitle("Details de l'Offre");
        setLayout(BoxLayout.y());
        
        /* *** *YOUR CODE GOES HERE* *** */
        /* *l affichage img* */
       
        Label labelnom_offre = new Label("nom offre :"+o.getNomOffre());
        Label labeldescription_offre= new Label("description offre :"+o.getDescriptionOffre());
        Label labelprix_offre= new Label("prix offre :"+o.getPrixOffre());
        Label labelreduction= new Label("reduction :"+o.getReduction());
        Label labeldate_debut_offre= new Label("date debut offre:"+o.getDateDebutOffre());
         Label labeldate_fin_offre= new Label("date fin offre :"+o.getDateFinOffre());
         
        Button next = new Button("Retour ");
        next.addActionListener(e -> new ListOffreForm(current));
        addAll(labelnom_offre, labeldescription_offre, labelprix_offre,labelreduction,labeldate_debut_offre,labeldate_fin_offre,next);
     
    }
}