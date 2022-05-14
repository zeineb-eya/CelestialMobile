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
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.services.ServicePlaninng;

/**
 *
 * @author skanr
 */
public class ModifPlaninngForm extends Form{
    
    Form current;
    ModifPlaninngForm(Planinng o) {
        setTitle("Modifier planinng");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(o.getId()), "id_planinng");
        tfID.setVisible(false);
        
        TextField tfnom_planning = new TextField(String.valueOf(o.getNomPlaninng()),"Nom planinng");
        TextField tfdescription_planning= new TextField(String.valueOf(o.getDescriptionPlaninng()), "description planinng");
        TextField tfdestination_planning= new TextField(String.valueOf(o.getDestinationPlaninng()), "destination planinng");
         TextField tfprix_planning= new TextField("", "prix");
       // TextField tfreduction= new TextField(String.valueOf(o.getReduction()), "reduction");
         TextField tfperiode_planning= new TextField("", "periode");
        TextField tfdateDebut_planning= new TextField(String.valueOf(o.getDateDebutPlaninng()), "dateDebut_planning");
        TextField tfdateFin_planning= new TextField(String.valueOf(o.getDateFinPlaninng()), "dateFin_planning");
      tfnom_planning.setUIID("TextFieldBlack");
        tfdescription_planning.setUIID("TextFieldBlack");
         tfdestination_planning.setUIID("TextFieldBlack");
         tfprix_planning.setUIID("TextFieldBlack");
         tfperiode_planning.setUIID("TextFieldBlack");
        tfdateDebut_planning.setUIID("TextFieldBlack");
         tfdateFin_planning.setUIID("TextFieldBlack");
        
        Button btnModifier = new Button("Modifier");
        Button next = new Button("suivant");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfnom_planning.getText().length()==0)||( tfdescription_planning.getText().length()==0)||( tfdestination_planning.getText().length()==0)||(tfprix_planning.getText().length()==0)||(tfperiode_planning.getText().length()==0)||(tfdateDebut_planning.getText().length()==0)||(tfdateFin_planning.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez verifier votre saisie", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                 
                    // Offre o = new Offre(Integer.parseInt(tfID.getText()),tfnom_offre.getText(),tfdescription_offre.getText(), Integer.parseInt(tfprix_offre.getText()),Integer.parseInt(tfreduction.getText()),tfdate_debut_offre.getText().toString(),tfdate_fin_offre.getText().toString());
                    Planinng o = new Planinng(Integer.parseInt(tfID.getText()),tfnom_planning.getText().toString(),tfdescription_planning.getText().toString(),tfdestination_planning.getText().toString(),Integer.parseInt(tfprix_planning.getText()), Integer.parseInt(tfperiode_planning.getText()),tfdateDebut_planning.getText().toString(),tfdateFin_planning.getText().toString() );
                    System.out.println(o.getId());
                    System.out.println(o.getNomPlaninng());
                    System.out.println(o.getDescriptionPlaninng());
                    System.out.println(o.getDestinationPlaninng());
                    System.out.println(o.getPrixPlaninng());
                    System.out.println(o.getPeriodePlaninng());
                    System.out.println(o.getDateDebutPlaninng());
                    System.out.println(o.getDateFinPlaninng());

                    if (ServicePlaninng.getInstance().modifPlaninng(o)) {
                        Dialog.show("Success", "planinng modifié avec succés", new Command("OK"));
                        
                    } else {
                        Dialog.show("ERROR", "connexion echoué", new Command("OK"));
                    }

                }
            }
        });
        next.addActionListener(e -> new ListPlaninngForm(current));
        addAll(tfID,tfnom_planning,tfdescription_planning,tfdestination_planning,tfprix_planning,tfperiode_planning,tfdateDebut_planning,tfdateFin_planning,btnModifier,next);
      
    }
}
    

