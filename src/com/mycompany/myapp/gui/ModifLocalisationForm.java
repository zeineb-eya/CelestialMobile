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
import com.mycompany.myapp.entities.Localisation;
import com.mycompany.myapp.services.ServiceLocalisation;

/**
 *
 * @author skanr
 */
public class ModifLocalisationForm extends Form{
    
    Form current;
    ModifLocalisationForm(Localisation o) {
        setTitle("Modifier localisation");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(o.getId()), "id_localisation");
        tfID.setVisible(false);
        
        TextField tfpositionDepart_localisation = new TextField(String.valueOf(o.getPositionDepartLocalisation()),"Position Depart");
        TextField tfpositionArivee_planning= new TextField(String.valueOf(o.getPositionAriveePlanning()), "Position Arrive");
        TextField tffusee= new TextField(String.valueOf(o.getFusee()), "Fusse");
        TextField tfheureDepart_localisation= new TextField(String.valueOf(o.getHeureDepartLocalisation()), "heure depart");
        TextField tfheureArrivee_loacalisation= new TextField(String.valueOf(o.getHeureArriveeLoacalisation()), "heure arrive");
        tfpositionDepart_localisation.setUIID("TextFieldBlack");
        tfpositionArivee_planning.setUIID("TextFieldBlack");
         tffusee.setUIID("TextFieldBlack");
         tfheureDepart_localisation.setUIID("TextFieldBlack");
         tfheureArrivee_loacalisation.setUIID("TextFieldBlack");
        
        Button btnModifier = new Button("Modifier");
        Button next = new Button("suivant");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfpositionDepart_localisation.getText().length()==0)||( tfpositionArivee_planning.getText().length()==0)||( tffusee.getText().length()==0)||(tfheureDepart_localisation.getText().length()==0)||(tfheureArrivee_loacalisation.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez verifier votre saisie", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                 
                    // Offre o = new Offre(Integer.parseInt(tfID.getText()),tfnom_offre.getText(),tfdescription_offre.getText(), Integer.parseInt(tfprix_offre.getText()),Integer.parseInt(tfreduction.getText()),tfdate_debut_offre.getText().toString(),tfdate_fin_offre.getText().toString());
                    Localisation o = new Localisation(Integer.parseInt(tfID.getText()),tfpositionDepart_localisation.getText().toString(),tfpositionArivee_planning.getText().toString(),tffusee.getText().toString(),tfheureDepart_localisation.getText().toString(),tfheureArrivee_loacalisation.getText().toString() );
                    System.out.println(o.getId());
                    System.out.println(o.getPositionDepartLocalisation());
                    System.out.println(o.getPositionAriveePlanning());
                    System.out.println(o.getFusee());
                    System.out.println(o.getHeureDepartLocalisation());
                    System.out.println(o.getHeureArriveeLoacalisation());

                    if (ServiceLocalisation.getInstance().modifLocalisation(o)) {
                        Dialog.show("Success", "localisation modifié avec succés", new Command("OK"));
                        
                    } else {
                        Dialog.show("ERROR", "connexion echoué", new Command("OK"));
                    }

                }
            }
        });
        next.addActionListener(e -> new ListLocalisationForm(current));
        addAll(tfpositionDepart_localisation,tfpositionArivee_planning,tffusee,tfheureDepart_localisation,tfheureArrivee_loacalisation,next);
      
    }
}
    

