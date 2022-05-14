/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Planinng;
import com.mycompany.myapp.services.ServicePlaninng;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.messaging.Message;
//import com.mycompany.myapp.services.ServiceReservation;

/**
 *
 * @author HP
 */
public class DetailPlaninngForm extends Form {
    Form current;
     ServicePlaninng ServicePlaninng = new ServicePlaninng();
    
     DetailPlaninngForm(Planinng o) {
                 /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Randonnee Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
       
        Label labelnom_planning = new Label("nom planinng :"+o.getNomPlaninng());
        Label labeldescription_planning = new Label("description :"+o.getDescriptionPlaninng());
        Label labeldestination_planning = new Label("destination :"+o.getDestinationPlaninng());
        Label labelprix_planning = new Label("prix :"+o.getPrixPlaninng());
        Label labelperiode_planning = new Label("periode :"+o.getPeriodePlaninng());
         Label labeldateDebut_planning = new Label("date debut :"+o.getDateDebutPlaninng());
        Label labeldateFin_planning = new Label("date fin :"+o.getDateFinPlaninng());
       
        Button showButton = new Button("");
        showButton.addActionListener(evt -> {
           new AjoutLocalisationForm().show();
        });
        addAll(labelnom_planning, labeldescription_planning, labeldestination_planning,labelprix_planning, labelperiode_planning,labeldateDebut_planning,labeldateFin_planning,showButton);
        /* *** *BACK BUTTON* *** */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
//        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
//            //SENDING EMAIL
//            Display.getInstance().sendMessage(new String[]{""}, "Let's participate this!", new Message("Check out this Ticket: " + b.getChairBillet() + " it's trip number: " + b.getVoyageNum() + " date "+b.getLocalisation()));
//        });
    }

}