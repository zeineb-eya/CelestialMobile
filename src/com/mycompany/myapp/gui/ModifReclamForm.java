/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author HP
 */
public class ModifReclamForm extends Form{
    Form current;
    ModifReclamForm(Reclamation r) {
        setTitle("Modifier réclamatione");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(r.getId()), "id_reclam");
        tfID.setVisible(false);
        
        TextField tfdescription_reclamation= new TextField(String.valueOf(r.getDescriptionReclamation()), "description reclamation");
         tfdescription_reclamation.setUIID("TextFieldBlack");
        //TextField tfdate_reclamation= new TextField(String.valueOf(r.getDateReclamation()), "date reclamation");
       // TextField tfetat_reclamation= new TextField(String.valueOf(r.getEtatReclamation()), "etat reclamation");
      // TextField tfuser= new TextField(String.valueOf(r.getUser()), "");
       
        Button btnModifierReclam = new Button("Modifier");
        Button next = new Button("retour");
        btnModifierReclam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfdescription_reclamation.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez verifier votre saisie", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                     System.out.println(tfdescription_reclamation.getText());
                      //System.out.println(tfdate_reclamation.getText());
                       //System.out.println(tfetat_reclamation.getText());
                      //  System.out.println(tfuser.getText());
                 
                    // Offre o = new Offre(Integer.parseInt(tfID.getText()),tfnom_offre.getText(),tfdescription_offre.getText(), Integer.parseInt(tfprix_offre.getText()),Integer.parseInt(tfreduction.getText()),tfdate_debut_offre.getText().toString(),tfdate_fin_offre.getText().toString());
                    Reclamation r = new Reclamation(Integer.parseInt(tfID.getText()),tfdescription_reclamation.getText().toString());
                    System.out.println(r.getId());
                    System.out.println(r.getDescriptionReclamation());
                  //  System.out.println(r.getUser());
                   
                    if (ServiceReclamation.getInstance().modifReclamation(r)) {
                        Dialog.show("Succes", "Réclamation modifié avec succés", new Command("OK"));
                        
                    } else {
                        Dialog.show("ERROR", "connexion echoué", new Command("OK"));
                    }

                }
            }
        });
        next.addActionListener(e -> new ListReclamForm(current));
        addAll(tfID,tfdescription_reclamation,btnModifierReclam,next);
      
    }
}
