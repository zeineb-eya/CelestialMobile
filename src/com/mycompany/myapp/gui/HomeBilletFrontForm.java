/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceBillet;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class HomeBilletFrontForm extends Form{
 

     Billet b;
    Resources res;
    public HomeBilletFrontForm(Form current) {
        
      
        setTitle("Billets");
        setLayout(BoxLayout.y());
         add(new Label("Choose an option"));

       Button btnListBillets = new Button("List Billets");
        Button btnListReservations = new Button("List reservations");
        Button btnOrderedByDate = new Button("Ordered By Date");
        Button btnOrderedByEtat = new Button("Ordered By Etat");
        Button btnOrderedByMail = new Button("Ordered By Mail");
        
        btnListBillets.addActionListener(e-> new ListBilletForm(current));
        btnListReservations .addActionListener(e-> new ListReservationForm(current));
        btnOrderedByDate.addActionListener(e-> new ReservationOrderedByDateForm(current).show());
        btnOrderedByEtat.addActionListener(e-> new ReservationOrderedByEtatForm(current).show());
        btnOrderedByMail.addActionListener(e-> new ReservationOrderedByMailForm(current).show());
        
  
      
       
    
        
    
        addAll(btnListBillets,btnListReservations,btnOrderedByDate,btnOrderedByEtat,btnOrderedByMail);
          
        
    }
}
