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
//import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.entities.Reservation;
//import com.mycompany.myapp.services.ServiceBillet;
import com.mycompany.myapp.services.ServiceReservation;
/**
 *
 * @author HP
 */
public class UpdateFormReservation extends Form {
    Form current;
     UpdateFormReservation(Reservation r) {
        setTitle("update Reservation");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(r.getId()), "ReservationID");
        tfID.setVisible(false);
        
//        TextField tfdate_reservation= new TextField(String.valueOf(r.getDateReservation()), "date_reservation");
//        TextField tfEtat_reservation= new TextField(String.valueOf(r.getEtatReservation()), "Etat_reservation");
        TextField tfuser= new TextField(String.valueOf(r.getUser()), "user");
        TextField tfbillet= new TextField(String.valueOf(r.getBillet()), "billet");
        
//        tfdate_reservation.setUIID("TextFieldBlack");
//        tfEtat_reservation.setUIID("TextFieldBlack");
        tfuser.setUIID("TextFieldBlack");
        tfbillet.setUIID("TextFieldBlack");
       /* Picker datePicker =new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String.valueOf(datePicker);*/

                
        Button btnSubmit = new Button("Update");
        Button next = new Button("next");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              if (/*(tfdate_reservation.getText().length()==0)||(tfEtat_reservation.getText().length()==0)  ||*/ (tfuser.getText().length()==0)|| (tfbillet.getText().length()==0)) {
                    Dialog.show("Alert", "please fill all fields", new Command("OK"));
                } else {
                    System.out.println(tfID.getText());
                   // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Reservation r = new Reservation (Integer.parseInt(tfID.getText()),Integer.parseInt(tfuser.getText()) , Integer.parseInt(tfbillet.getText()) );
                    System.out.println(r.getId());
                    System.out.println(r.getDateReservation());
                    System.out.println(r.getEtatReservation());
                    System.out.println(r.getUser());
                    System.out.println(r.getBillet());
                   
                    if (ServiceReservation.getInstance().updateReservation(r)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        //new ListBilletForm(current);
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }

                }
            }
        });
         next.addActionListener(e -> new ListReservationForm(current));
        addAll(tfID,tfuser,tfbillet,btnSubmit,next);
       //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
