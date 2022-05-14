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
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.services.ServicePlaninng;

/**
 *
 * @author skanr
 */
public class DeletePlaninngForm extends Form {
    

    
     DeletePlaninngForm(Resources res, Planinng o) {
        setTitle("Supprimer planinng");
        setLayout(BoxLayout.y());
   
        
        TextField tfID = new TextField ("", "BilletID");
        Button btnSupprimer = new Button("Supprimer");
        
         btnSupprimer.addActionListener(new ActionListener() {
             
      @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
//                    Billet b = new Billet(tfID.getText());
                    System.out.println(o.getId());
                    System.out.println(o.getNomPlaninng());
                    System.out.println(o.getDescriptionPlaninng());
                    System.out.println(o.getDestinationPlaninng());
                    System.out.println(o.getPrixPlaninng());
                    System.out.println(o.getPeriodePlaninng());
                    System.out.println(o.getDateDebutPlaninng());
                    System.out.println(o.getDateFinPlaninng());
                    if (ServicePlaninng.getInstance().deletePlaninng(o)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, btnSupprimer);
       //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}

