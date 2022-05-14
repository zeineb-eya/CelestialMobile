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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
/**
 *
 * @author HP
 */
public class AjoutReclamForm extends Form{
    Form current;
    public AjoutReclamForm() {
        setTitle("réclamer");
        setLayout(BoxLayout.y());
        
        TextField tfdescription_reclamation = new TextField("","description reclamation");
        tfdescription_reclamation.setUIID("TextFieldBlack");
        
        //addStringValue("date_reclamation",datePicker);
        Button btnAddReclamation = new Button("Reclamer");
         //btnAddReclamation.setBackground(Color.red);
        Button next = new Button("retour");
        
        btnAddReclamation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdescription_reclamation.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Reclamation r = new Reclamation(tfdescription_reclamation.getText());
                      //  Offre o = new Offre(Integer.parseInt((tfnom_offre.getText().length()==0)),(tfdescription_offre.getText().length()==0),(tfdate_debut_offre.getText().length()==0),(tfdate_fin_offre.getText().length()==0));
                        if( ServiceReclamation.getInstance().addReclamation(r))
                        {
                           Dialog.show("Succés","Nous avons bien recu votre réclamation",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Veuillez vérifier vos champs", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        next.addActionListener(e -> new ListReclamForm(current));
        addAll(tfdescription_reclamation,btnAddReclamation,next);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
}

    AjoutReclamForm(Resources globalResources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

