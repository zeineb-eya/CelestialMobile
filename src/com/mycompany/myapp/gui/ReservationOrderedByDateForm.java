/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
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
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;

import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class ReservationOrderedByDateForm extends Form{
    Form current;
    ServiceReservation ServiceReservation = new ServiceReservation();
    ArrayList<Reservation> ReservationArrayList = new ArrayList<>();
    public ReservationOrderedByDateForm(Form previous) {
//        
current = this;
        setTitle("Planning List");
        setLayout(BoxLayout.y());

        ReservationArrayList = ServiceReservation.showOrderedByDate();

              
        for (Reservation reservation : ReservationArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(reservation.getDateReservation());
            multiButton.setTextLine2(reservation.getEtatReservation());
            multiButton.setTextLine3(reservation.getUser()+ "");
            multiButton.setTextLine4(reservation.getBillet()+ "");  
            multiButton.setUIID(reservation.getId() + "");
            multiButton.addActionListener(l -> new ListReservationForm(current).show());
            add(multiButton);
        }
       
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    String line3 = mb.getTextLine3();
                    String line4 = mb.getTextLine4();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1
                            ||line3 != null && line3.toLowerCase().indexOf(text) > -1 ||
                            line4 != null && line4.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeReservationForm(current).showBack());

        getToolbar().addCommandToOverflowMenu("Shuffle", FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ListReservationForm(null).show();
        });
        
    }



}
