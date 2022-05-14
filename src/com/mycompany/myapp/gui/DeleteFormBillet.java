/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
/**
 *
 * @author HP
 */
public class DeleteFormBillet extends Form  {
    //Form previous;
    DeleteFormBillet(Billet b, Resources res) {
        setTitle("delete Billet");
        setLayout(BoxLayout.y());
//        Billet b = new Billet();
 //       ComboBox cb = new ComboBox();
        
        
        TextField tfID = new TextField("", "BilletID");
        Button btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
//                    Billet b = new Billet(tfID.getText());
                    System.out.println(b.getId());
                    System.out.println(b.getChairBillet());
                    System.out.println(b.getVoyageNum());
                    System.out.println(b.getTerminal());
                    System.out.println(b.getPortail());
                    System.out.println(b.getEmbarquement());
                    System.out.println(b.getLocalisation());
                    if (ServiceBillet.getInstance().deleteBillet(b)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        addAll(tfID, btnSubmit);
       //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
