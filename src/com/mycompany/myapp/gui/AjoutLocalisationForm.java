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
public class AjoutLocalisationForm extends Form {
    
     Form current;
    public AjoutLocalisationForm() {
        setTitle("Ajouter une nouvelle localisation");
        setLayout(BoxLayout.y());
        
        TextField tfpositionDepart_localisation = new TextField("","Position depart");
        TextField tfpositionArivee_planning= new TextField("", "Position arrive");
        TextField tffusee= new TextField("", "fusee");
        TextField tfheureDepart_localisation = new TextField("", "heure depart");
        TextField tfheureArrivee_loacalisation = new TextField("", "heure arrive");
        Button btnAddLocalisation = new Button("Ajouter localisation");
        
        tfpositionDepart_localisation.setUIID("TextFieldBlack");
        tfpositionArivee_planning.setUIID("TextFieldBlack");
         tffusee.setUIID("TextFieldBlack");
         tfheureDepart_localisation.setUIID("TextFieldBlack");
         tfheureArrivee_loacalisation.setUIID("TextFieldBlack");
        
        Button next = new Button("suivant");
        btnAddLocalisation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfpositionDepart_localisation.getText().length()==0)||( tfpositionArivee_planning.getText().length()==0)||( tffusee.getText().length()==0)||(tfheureDepart_localisation.getText().length()==0)||(tfheureArrivee_loacalisation.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Localisation o = new Localisation(tfpositionDepart_localisation.getText(),tfpositionArivee_planning.getText(),tffusee.getText(),tfheureDepart_localisation.getText().toString(),tfheureArrivee_loacalisation.getText().toString());
                      //  Offre o = new Offre(Integer.parseInt((tfnom_offre.getText().length()==0)),(tfdescription_offre.getText().length()==0),(tfdate_debut_offre.getText().length()==0),(tfdate_fin_offre.getText().length()==0));
                        if( ServiceLocalisation.getInstance().addLocalisation(o))
                        {
                           Dialog.show("Succés","Votre localisation a été bien ajouté",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Veuillez vérifier vos champs", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        next.addActionListener(e -> new ListLocalisationForm(current));
        addAll(btnAddLocalisation,tfpositionDepart_localisation,tfpositionArivee_planning,tffusee,tfheureDepart_localisation,tfheureArrivee_loacalisation,next);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
}