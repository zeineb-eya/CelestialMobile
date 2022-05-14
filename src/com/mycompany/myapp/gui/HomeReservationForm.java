/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author HP
 */
public class HomeReservationForm extends Form {
    
    
    public HomeReservationForm(Form current) {
       
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
      //  Button btnAddReservation = new Button("reserve"); //reservation tsir mel affichage ta3 billets
//        Button btnListReservations = new Button("List reservatiions");
//        Button btnOrderedByDate = new Button("Ordered By Date");
//        Button btnOrderedByEtat = new Button("Ordered By Etat");
//        Button btnOrderedByMail = new Button("Ordered By Mail");
        Button StatFormReservation = new Button("List Reservation");
         Button btnListBillets = new Button("List Billets");
      //  btnAddReservation.addActionListener(e-> new AddReservationForm().show());
//        btnListReservations .addActionListener(e-> new ListReservationForm(current));
//        btnOrderedByDate.addActionListener(e-> new ReservationOrderedByDateForm(current).show());
//        btnOrderedByEtat.addActionListener(e-> new ReservationOrderedByEtatForm(current).show());
//        btnOrderedByMail.addActionListener(e-> new ReservationOrderedByMailForm(current).show());
         StatFormReservation.addActionListener(e-> new ListResForm().show());
        btnListBillets.addActionListener(e-> new ListBiForm().show());
        addAll(/*btnListReservations,btnOrderedByDate,btnOrderedByEtat,btnOrderedByMail,*/StatFormReservation,btnListBillets);
          
        
    }
}
