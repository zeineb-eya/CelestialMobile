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
import com.mycompany.myapp.entities.Localisation;
import com.mycompany.myapp.services.ServiceLocalisation;

/**
 *
 * @author skanr
 */
public class DeleteLocalisationForm extends Form {
    

    
     DeleteLocalisationForm(Resources res, Localisation o) {
        setTitle("Supprimer localisation");
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
                    System.out.println(o.getPositionDepartLocalisation());
                    System.out.println(o.getPositionAriveePlanning());
                    System.out.println(o.getFusee());
                    System.out.println(o.getHeureDepartLocalisation());
                    System.out.println(o.getHeureArriveeLoacalisation());

                    if (ServiceLocalisation.getInstance().deleteLocalisation(o)) {
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

