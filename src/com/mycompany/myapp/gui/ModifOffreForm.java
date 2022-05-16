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
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.ServiceOffre;

/**
 *
 * @author HP
 */
public class ModifOffreForm extends Form {
     Form current;
    ModifOffreForm(Offre o) {
        setTitle("Modifier offre");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(o.getId()), "id_offre");
        tfID.setVisible(false);
        
        TextField tfnom_offre = new TextField(String.valueOf(o.getNomOffre()),"Nom offre");
        tfnom_offre.setUIID("TextFieldBlack");
        TextField tfdescription_offre= new TextField(String.valueOf(o.getDescriptionOffre()), "description offre");
        tfdescription_offre.setUIID("TextFieldBlack");
        //TextField tfprix_offre = new TextField(String.valueOf(o.getPrixOffre()),"prix_offre");
         TextField tfprix_offre= new TextField("", "prix");
         tfprix_offre.setUIID("TextFieldBlack");
       // TextField tfreduction= new TextField(String.valueOf(o.getReduction()), "reduction");
         TextField tfreduction= new TextField("", "reduction");
         tfreduction.setUIID("TextFieldBlack");
        TextField tfdate_debut_offre= new TextField(String.valueOf(o.getDateDebutOffre()), "date_debut_offre");
        tfdate_debut_offre.setUIID("TextFieldBlack");
        TextField tfdate_fin_offre= new TextField(String.valueOf(o.getDateFinOffre()), "date_fin_offre");
        tfdate_fin_offre.setUIID("TextFieldBlack");
      
        
        Button btnModifier = new Button("Modifier");
        Button next = new Button("retour");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfnom_offre.getText().length()==0)||(tfdescription_offre.getText().length()==0)||(tfprix_offre.getText().length()==0)||(tfreduction.getText().length()==0)||(tfdate_debut_offre.getText().length()==0)||(tfdate_fin_offre.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez verifier votre saisie", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                 
                    // Offre o = new Offre(Integer.parseInt(tfID.getText()),tfnom_offre.getText(),tfdescription_offre.getText(), Integer.parseInt(tfprix_offre.getText()),Integer.parseInt(tfreduction.getText()),tfdate_debut_offre.getText().toString(),tfdate_fin_offre.getText().toString());
                    Offre o = new Offre(Integer.parseInt(tfID.getText()),tfnom_offre.getText().toString(),tfdescription_offre.getText().toString(),Integer.parseInt(tfprix_offre.getText()), Integer.parseInt(tfreduction.getText()),tfdate_debut_offre.getText().toString(),tfdate_fin_offre.getText().toString() );
                    System.out.println(o.getId());
                    System.out.println(o.getNomOffre());
                    System.out.println(o.getDescriptionOffre());
                    System.out.println(o.getPrixOffre());
                    System.out.println(o.getReduction());
                    System.out.println(o.getDateDebutOffre());
                    System.out.println(o.getDateFinOffre());

                    if (ServiceOffre.getInstance().modifOffre(o)) {
                        Dialog.show("Success", "Offre modifié avec succés", new Command("OK"));
                        
                    } else {
                        Dialog.show("ERROR", "connexion echoué", new Command("OK"));
                    }

                }
            }
        });
        next.addActionListener(e -> new ListOffreForm(current));
        addAll(tfID,tfnom_offre,tfdescription_offre,tfprix_offre,tfreduction,tfdate_debut_offre,tfdate_fin_offre,btnModifier,next);
      
    }

    


}
