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
import com.mycompany.myapp.services.ServicePlaninng;
import com.mycompany.myapp.entities.Planinng;

/**
 *
 * @author skanr
 */
public class AjoutPlaninngForm extends Form {
    
     Form current;
    public AjoutPlaninngForm() {
        setTitle("Ajouter un nouvelle planinng");
        setLayout(BoxLayout.y());
        
        TextField tfnom_planning = new TextField("","Nom planning");
        TextField tfdescription_planning= new TextField("", "Description planning");
        TextField tfdestination_planning= new TextField("", "destination planning");
        TextField tfprix_planning = new TextField("","prix planning");
        TextField tfperiode_planning= new TextField("", "periode planning");
        TextField tfdateDebut_planning = new TextField("", "date debut planinng");
        TextField tfdateFin_planning = new TextField("", "date fin planinng");
        
         tfnom_planning.setUIID("TextFieldBlack");
        tfdescription_planning.setUIID("TextFieldBlack");
         tfdestination_planning.setUIID("TextFieldBlack");
         tfprix_planning.setUIID("TextFieldBlack");
         tfperiode_planning.setUIID("TextFieldBlack");
        tfdateDebut_planning.setUIID("TextFieldBlack");
         tfdateFin_planning.setUIID("TextFieldBlack");
         
        Button btnAddPlaninng = new Button("Ajouter planinng");
        Button next = new Button("suivant");
        btnAddPlaninng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom_planning.getText().length()==0)||( tfdescription_planning.getText().length()==0)||( tfdestination_planning.getText().length()==0)||(tfprix_planning.getText().length()==0)||(tfperiode_planning.getText().length()==0)||(tfdateDebut_planning.getText().length()==0)||(tfdateFin_planning.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Planinng o = new Planinng(tfnom_planning.getText(),tfdescription_planning.getText(),tfdestination_planning.getText(), Integer.parseInt(tfprix_planning.getText()),Integer.parseInt(tfperiode_planning.getText()),tfdateDebut_planning.getText().toString(),tfdateFin_planning.getText().toString());
                      //  Offre o = new Offre(Integer.parseInt((tfnom_offre.getText().length()==0)),(tfdescription_offre.getText().length()==0),(tfdate_debut_offre.getText().length()==0),(tfdate_fin_offre.getText().length()==0));
                        if( ServicePlaninng.getInstance().addPlaninng(o))
                        {
                           Dialog.show("Succés","Votre planinng a été bien ajouté",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Veuillez vérifier vos champs", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        next.addActionListener(e -> new ListPlaninngForm(current));
        addAll(tfnom_planning,tfdescription_planning,tfdestination_planning,tfprix_planning,tfperiode_planning,tfdateDebut_planning,tfdateFin_planning,btnAddPlaninng,next);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
}
