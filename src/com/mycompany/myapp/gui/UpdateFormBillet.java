/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
/**
 *
 * @author HP
 */
public class UpdateFormBillet extends Form {
    
    Form current;
    UpdateFormBillet(Billet b) {
        setTitle("update Billet");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(b.getId()), "BilletID");
        tfID.setVisible(false);
        
        TextField tfchair_billet = new TextField(String.valueOf(b.getChairBillet()),"Chair number");
        TextField tfvoyage_num= new TextField(String.valueOf(b.getVoyageNum()), "voyage number");
        TextField tfterminal = new TextField(String.valueOf(b.getTerminal()),"Terminal");
        TextField tfportail= new TextField(String.valueOf(b.getPortail()), "Portail");
        TextField tfembarquement= new TextField(String.valueOf(b.getEmbarquement()), "Embarquement");
        TextField tflocalisation= new TextField(String.valueOf(b.getLocalisation()), "localisation");
        
        tfchair_billet.setUIID("TextFieldBlack");
         tfvoyage_num.setUIID("TextFieldBlack");
         tfterminal.setUIID("TextFieldBlack");
         tfportail.setUIID("TextFieldBlack");
         tfembarquement.setUIID("TextFieldBlack");
         tflocalisation.setUIID("TextFieldBlack");
       /* Picker datePicker =new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String.valueOf(datePicker);*/

                
        Button btnSubmit = new Button("Submit");
        Button next = new Button("next");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfchair_billet.getText().length()==0)||(tfvoyage_num.getText().length()==0)||(tfterminal.getText().length()==0)||(tfportail.getText().length()==0)||(tfembarquement.getText().length()==0)||(tflocalisation.getText().length()==0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                   // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Billet b = new Billet(Integer.parseInt(tfID.getText()),Integer.parseInt(tfchair_billet.getText()), Integer.parseInt(tfvoyage_num.getText()), Integer.parseInt(tfterminal.getText()), Integer.parseInt(tfportail.getText()), tfembarquement.getText().toString(), Integer.parseInt(tflocalisation.getText()) );
                    System.out.println(b.getId());
                    System.out.println(b.getChairBillet());
                    System.out.println(b.getVoyageNum());
                    System.out.println(b.getTerminal());
                    System.out.println(b.getPortail());
                    System.out.println(b.getEmbarquement());
                    System.out.println(b.getLocalisation());

                    if (ServiceBillet.getInstance().updateBillet(b)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        //new ListBilletForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
        next.addActionListener(e -> new ListBilletForm(current));
        addAll(tfID,tfchair_billet,tfvoyage_num,tfterminal,tfportail,tfembarquement,tflocalisation ,btnSubmit,next);
       //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
