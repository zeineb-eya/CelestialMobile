/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;
/**
 *
 * @author HP
 */
public class AddReservationForm extends Form {
    Form current;
  
    public AddReservationForm() {
        setTitle("Reserve Ticket");
        setLayout(BoxLayout.y());
      
//        TextField tfdate_reservation= new TextField("", "date_reservation");
//        TextField tfEtat_reservation= new TextField("", "Etat_reservation");
        TextField tfuser= new TextField("", "user");
        TextField tfbillet= new TextField("", "billet");
        
//        tfdate_reservation.setUIID("TextFieldBlack");
//        tfEtat_reservation.setUIID("TextFieldBlack");
        tfuser.setUIID("TextFieldBlack");
        tfbillet.setUIID("TextFieldBlack");
        
        Button btnValider = new Button("Reserve");
        Button next = new Button("next");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (/*(tfdate_reservation.getText().length()==0)||(tfEtat_reservation.getText().length()==0)  ||*/ (tfuser.getText().length()==0)|| (tfbillet.getText().length()==0))
                      
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reservation r = new Reservation( /*tfdate_reservation.getText().toString(), tfEtat_reservation.getText().toString(),*/ Integer.parseInt(tfuser.getText()) , Integer.parseInt(tfbillet.getText()));
                        if( ServiceReservation.getInstance().addReservation(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
             
                
            }
        });
        next.addActionListener(e -> new ListReservationForm(current));
        addAll(/*tfdate_reservation,tfEtat_reservation,*/tfuser,tfbillet,btnValider,next);
  
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
