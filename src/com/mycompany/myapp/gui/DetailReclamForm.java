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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author HP
 */
public class DetailReclamForm extends Form{
    Form current;
     ServiceReclamation ServiceReclam = new ServiceReclamation();
    
     DetailReclamForm(Reclamation r) {
                 /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Detail reclamation");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *l affichage img* */
       
        Label description_reclamation = new Label("Description reclam :"+r.getDescriptionReclamation());
        Label date_reclamation = new Label("date reclamation :"+r.getDateReclamation());
        Label etat_reclamation = new Label("etat reclamation :"+r.getEtatReclamation());
         Button next = new Button("Retour ");
        next.addActionListener(e -> new ListReclamForm(current));
        addAll(description_reclamation, date_reclamation, etat_reclamation,next);
     
    }

}

